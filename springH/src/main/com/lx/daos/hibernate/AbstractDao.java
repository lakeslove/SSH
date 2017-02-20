package com.lx.daos.hibernate;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.lx.daos.Dao;

@SuppressWarnings("unchecked")
public class AbstractDao<T, ID extends Serializable> extends HibernateDaoSupport implements Dao<T, ID> {
	private static final Logger log = LogManager.getLogger(AbstractDao.class); 
	
//	public abstract Class<T> getModelClass() throws DataAccessException;
	
	@Override
	public Session openSession(){
		return this.getSessionFactory().openSession();
	}
	
	@Override
	public Session getSession(){
		return this.getSessionFactory().getCurrentSession();
	}
	
	//利用反射来获取调用此方法的类
	@Override
	public Class<T> getModelClass() throws DataAccessException {
		Class<?> clz = this.getClass();
		// 得到子类对象的泛型父类类型（也就是BaseDaoImpl<T>）
		ParameterizedType type = (ParameterizedType) clz.getGenericSuperclass();
		System.out.println(type);
		Type[] types = type.getActualTypeArguments();
		Class<T> entityClass = (Class<T>) types[0];
		return entityClass;
	}
	@Override
	public void flush() throws DataAccessException {
		getHibernateTemplate().flush();
	}
	@Override
	public void refresh(T t) throws DataAccessException {
		getHibernateTemplate().refresh(t);;
	}
	@Override
	public void clear() throws DataAccessException {
		getHibernateTemplate().clear();
	}
	
	//获取数据的方法
	@Override
	public T get(ID id) throws DataAccessException {
		return (T) getHibernateTemplate().get(getModelClass(), id);
		// return (T)getHibernateTemplate().load(getModelClass(), id);
	}
	@Override
	public List<T> find(String sql) throws DataAccessException {
		return (List<T>) getHibernateTemplate().find(sql);
	}
	@Override
	public List<T> findByIds(ID[] ids) throws DataAccessException {
		return (List<T>) getHibernateTemplate()
				.find("FROM " + getModelClass().getSimpleName() + " obj WHERE obj.id in " + this.getString(ids));
	}
	@Override
	public List<T> findByInValues(String propertyName,Object[] values) throws DataAccessException {
		return (List<T>) getHibernateTemplate()
				.find("FROM " + getModelClass().getSimpleName() + " obj WHERE obj."+propertyName+" in " + this.getString(values));
	}
	@Override
	public List<T> findAll() throws DataAccessException {
		return (List<T>) getHibernateTemplate().find("FROM " + getModelClass().getSimpleName() + " ORDER BY id");
	}
	@Override
	public List<T> findAllOrderBy(String order) throws DataAccessException {
		Criteria cri = getSessionFactory().getCurrentSession().createCriteria(getModelClass());
		cri.addOrder(Order.asc(order));
		return (List<T>) cri.list();
	}
	@Override
	public List<T> findAllDescOrderBy(String order) throws DataAccessException {
		Criteria cri = getSessionFactory().getCurrentSession().createCriteria(getModelClass());
		cri.addOrder(Order.desc(order));
		return (List<T>) cri.list();
	}
	
	private String getString(Object[] ids) {
		StringBuffer sIds = new StringBuffer();
		sIds.append("(");
		for (int i = 0; i < ids.length; i++) {
			sIds.append(String.valueOf(ids[i]));
			if (i != ids.length - 1) {
				sIds.append(",");
			}
		}
		sIds.append(")");
		return sIds.toString();
	}
	@Override
	public List<T> getListByProperty(String property, Object value) throws DataAccessException {
		String hql = String.format("from %s where %s=:val ", getModelClass().getSimpleName(), property);
		Query query = this.getSessionFactory().getCurrentSession().createQuery(hql);
		query.setParameter("val", value);
		return query.list();
	}
	@Override
	public List<T> getOrderListByProperty(String property, Object value,String orderProperty,String order) throws DataAccessException {
		String hql = String.format("from %s where %s=:val order by %s %s", getModelClass().getSimpleName(), property,orderProperty,order);
		Query query = this.getSessionFactory().getCurrentSession().createQuery(hql);
		query.setParameter("val", value);
		return query.list();
	}
	@Override
	public List<T> getOffsetLimitOrderList(String orderProperty,String order, int limit, int offset) throws DataAccessException {
		String hql = String.format("from %s order by %s %s", getModelClass().getSimpleName(),orderProperty,order);
		Query query = this.getSessionFactory().getCurrentSession().createQuery(hql);
		query.setFirstResult(offset);
		query.setMaxResults(limit);
		return query.list();
	}
	@Override
	public List<T> getOffsetLimitOrderListByProperty(String property, Object value,String orderProperty,String order, int limit, int offset) throws DataAccessException {
		String hql = String.format("from %s where %s = :val order by %s %s", getModelClass().getSimpleName(), property,orderProperty,order);
		Query query = this.getSessionFactory().getCurrentSession().createQuery(hql);
		query.setParameter("val", value);
		query.setFirstResult(offset);
		query.setMaxResults(limit);
		return query.list();
	}
	@Override
	public List<T> getAllByHql(String hql,Object[] paramArray) throws DataAccessException {
		Query query = getQueryBySqlAndObjects(hql,paramArray);
		
		return query.list();
	}
	
	@Override
	public List<T> findTopByCriteria(final DetachedCriteria detachedCriteria, final int top, final Order[] orders)
			throws DataAccessException {
		return (List<T>) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
				Criteria criteria = detachedCriteria.getExecutableCriteria(session);
				criteria.setProjection(null);
				if (orders != null) {
					for (Order order : orders) {
						criteria.addOrder(order);
					}
				}
				List<T> items = criteria.setCacheable(true).setFirstResult(0).setMaxResults(top).list();
				return items;
			}
		});
	}
	@Override
	@SuppressWarnings("rawtypes")
	public List<T> findAllByCriteria(final DetachedCriteria detachedCriteria) throws DataAccessException {
		return (List) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
				Criteria criteria = detachedCriteria.getExecutableCriteria(session);
				return (List<T>) criteria.list();
			}
		});
	}

	//获取数量
	@Override
	public long getCount() throws DataAccessException {

		String hql = String.format("select count(*) from %s", getModelClass().getSimpleName());
		Query query = this.getSessionFactory().getCurrentSession().createQuery(hql);
		return (long)query.list().get(0);
	}
	@Override
	public long getCount(String property, Object value) throws DataAccessException {

		String hql = String.format("select count(*) from %s where %s = :val1", getModelClass().getSimpleName(), property);
		Query query = this.getSessionFactory().getCurrentSession().createQuery(hql);
		query.setParameter("val1", value);
		return (long)query.list().get(0);
	}
	@Override
	public long getCountByPropertys(String propertyName,Object[] propertys) throws DataAccessException {
		String hql = "select count(*) FROM " + getModelClass().getSimpleName() + " WHERE "+propertyName+" in " + this.getString(propertys);
		Query query = this.getSessionFactory().getCurrentSession().createQuery(hql);
		return (long)query.list().get(0);
	}
	@Override
	public long getCountByHql(String hql) throws DataAccessException {
		Query query = this.getSessionFactory().getCurrentSession().createQuery(hql);
		return (long)query.list().get(0);
	}
	@Override
	public long getCountBySql(String sql) throws DataAccessException {
		Query query = this.getSessionFactory().getCurrentSession().createSQLQuery(sql);
		return (long)query.list().get(0);
	}
	@Override
	public int getCountByCriteria(final DetachedCriteria detachedCriteria) throws DataAccessException {
		Integer count = (Integer) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
				Criteria criteria = detachedCriteria.getExecutableCriteria(session);
				return criteria.setProjection(Projections.rowCount()).uniqueResult();
			}
		});
		return count.intValue();
	}
	@Override
	public int getUniqueCount() {
		Object count = getSessionFactory().getCurrentSession().createCriteria(getModelClass())
				.setProjection(Projections.rowCount()).uniqueResult();
		if (count == null) {
			return 0;
		}
		return Integer.valueOf(count.toString());
	}
	
	//删除数据的方法
	@Override
	public void delete(T t) throws DataAccessException {
		if (t != null) {
			getHibernateTemplate().delete(t);
			getHibernateTemplate().flush();
			getSessionFactory().getCurrentSession().flush();
			getSessionFactory().getCurrentSession().clear();
			// getHibernateTemplate().getSessionFactory().evictQueries();
		}
	} 
	@Override
	public Integer deleteByIds(ID[] ids) throws DataAccessException {
		return getHibernateTemplate().bulkUpdate(
				"delete FROM " + getModelClass().getSimpleName() + " obj WHERE obj.id in " + this.getString(ids));
	}
	@Override
	public long deleteByProperty(String property, Object value) throws DataAccessException {
		String hql = String.format("delete %s as u where u.%s = :val ", getModelClass().getSimpleName(), property);
		Query query = this.getSessionFactory().getCurrentSession().createQuery(hql);
		query.setParameter("val", value);
		long count = query.executeUpdate();
		return count;
	}
	@Override
	public void deleteByInValues(String propertyName,Object[] values) throws DataAccessException {
		getHibernateTemplate().bulkUpdate(
				"delete FROM " + getModelClass().getSimpleName() + " obj WHERE obj."+propertyName+" in " + this.getString(values));
	}
	@Override
	public void deleteAll() throws DataAccessException {
		getHibernateTemplate().bulkUpdate("delete " + getModelClass().getSimpleName());
	}
	@Override
	public void deleteAll(Collection<T> entities) throws DataAccessException {
		if (entities != null) {
			getHibernateTemplate().deleteAll(entities);
		}
	}

	//执行数据保存的方法
	@Override
	public ID save(T t) throws DataAccessException {
		if (t != null) {
			return (ID) getHibernateTemplate().save(t);
		}
		return null;
	}
	
	// 执行数据更新的方法
	@Override
	public void update(T t) throws DataAccessException {
		if (t != null) {
			getHibernateTemplate().update(t);
		}

	}
	@Override
	public Integer bulkUpdate(String sql){
		return getHibernateTemplate().bulkUpdate(sql);
	}
	@Override
	public void merge(T t) throws DataAccessException {
		if (t != null) {
			getHibernateTemplate().merge(t);
		}
	}
	@Override
	public void evict(T t) throws DataAccessException {
		if (t != null)
			getHibernateTemplate().evict(t);
	}
	@Override
	public Object max(String property) throws DataAccessException {
		Criteria cri = getSessionFactory().getCurrentSession().createCriteria(getModelClass());
		cri.setProjection(Projections.max(property));
		return cri.uniqueResult();
	}
	@Override
	public long getLastInsertId() throws DataAccessException {
		String hql = String.format("select max(a.id) from %s a", getModelClass().getSimpleName());
		long maxid = new Long((getSessionFactory().getCurrentSession().createQuery(hql).uniqueResult()).toString());
		return maxid;
	}
	
	//getSomething by map
	@Override
	public Long getCountByFields(Map<String,Object> FieldsMap,Boolean like) throws DataAccessException {
		String tmpSqlFromMap = getStringFromMap(FieldsMap,like);
		String modelName = getModelClass().getSimpleName();
		String hql = String.format("select count(*) from %s where 1=1 %s", modelName, tmpSqlFromMap);
		Query query = getQueryFromMapAndHql(FieldsMap,hql);
		return (Long)query.list().get(0);
	}
	@Override
	public List<T> getOffsetLimitOrderListByFields(Map<String,Object> FieldsMap,String orderProperty,String order, int limit, int offset,Boolean like) throws DataAccessException {
		Query query = getQueryFromMap(FieldsMap,like,true);
		query.setParameter("orderProperty", orderProperty);
		query.setParameter("order", order);
		query.setFirstResult(offset);
		query.setMaxResults(limit);
		return query.list();
	}
	@Override
	public List<T> getOrderListByFields(Map<String,Object> FieldsMap,String orderProperty,String order,Boolean like) throws DataAccessException {
		Query query = getQueryFromMap(FieldsMap,like,true);
		query.setParameter("orderProperty", orderProperty);
		query.setParameter("order", order);
		return query.list();
	}
	@Override
	public List<T> getOrderListByFields(Map<String,Object> FieldsMap,Boolean like) throws DataAccessException {
		Query query = getQueryFromMap(FieldsMap,like,false);
		return query.list();
	}
	@Override
	public Query getQueryFromMap(Map<String,Object> FieldsMap,Boolean like,Boolean order){
		String tmpSqlFromMap = getStringFromMap(FieldsMap,like);
		String modelName = getModelClass().getSimpleName();
		String hql;
		if(order){
			hql = String.format("from %s where 1=1 %s order by :orderProperty :order", modelName, tmpSqlFromMap);
		}else{
			hql = String.format("from %s where 1=1 %s", modelName, tmpSqlFromMap);
		}
		Query query = getQueryFromMapAndHql(FieldsMap,hql);
		return query;
	}
	
	public String getStringFromMap(Map<String,Object> FieldsMap,Boolean like){
		StringBuilder sb = new StringBuilder();
		if(like){
			for(Map.Entry<String, Object> entry : FieldsMap.entrySet()){
				String fieldName = entry.getKey();
				sb.append(fieldName + "like :"+fieldName+",");
			}
		}else{
			for(Map.Entry<String, Object> entry : FieldsMap.entrySet()){
				String fieldName = entry.getKey();
				sb.append(fieldName + "= :"+fieldName+",");
			}
		}
		sb.setLength(sb.length()-1);
		String hql = sb.toString();
		return hql;
	}
	@Override
	public Query getQueryFromMapAndHql(Map<String,Object> FieldsMap,String hql){
		Query query = this.getSessionFactory().getCurrentSession().createQuery(hql);
		for(Map.Entry<String, Object> entry : FieldsMap.entrySet()){
			query.setParameter(entry.getKey(), entry.getValue());
		}
		return query;
	}
	@Override
	public Query getQueryFromMapAndHql(Map<String,String> FieldsMap,Query query){
		for(Map.Entry<String, String> entry : FieldsMap.entrySet()){
			query.setParameter(entry.getKey(), entry.getValue()+"");
		}
		return query;
	}
	
	@Override
	public int executeUpdateBySql(String updateSql, Object[] paramArray) {
		Query query = getQueryBySqlAndObjects(updateSql,paramArray);
		return query.executeUpdate();
	}
	
	public Query getQueryByHqlAndObjects(String hql,Object[] paramArray){
		Query query = this.getSessionFactory().getCurrentSession().createQuery(hql);
		setQueryByParamArray(query,paramArray);
		return query;
	}
	
	public Query getQueryBySqlAndObjects(String sql,Object[] paramArray){
		Query query = this.getSessionFactory().getCurrentSession().createSQLQuery(sql);
		setQueryByParamArray(query,paramArray);
		return query;
	}
	
	public void setQueryByParamArray(Query query,Object[] paramArray){
		if (paramArray!=null) {
			for (int i=0; i< paramArray.length; i++) {
				query.setParameter(i, paramArray[i]);
			}
		}
	}
	
	@Autowired  
    public void setSessionFactoryOverride(SessionFactory sessionFactory)   
    {   
        super.setSessionFactory(sessionFactory);   
    }
	
	
}

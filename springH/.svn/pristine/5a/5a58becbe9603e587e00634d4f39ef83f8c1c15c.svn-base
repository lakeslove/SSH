package com.lx.daos.hibernate;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.lang.reflect.Type;

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
public abstract class AbstractDao<T, ID extends Serializable> extends HibernateDaoSupport implements Dao<T, ID> {
	private static final Logger log = LogManager.getLogger(AbstractDao.class); 
	
//	public abstract Class<T> getModelClass() throws DataAccessException;
//	
	public Class<T> getModelClass() throws DataAccessException {
		Class<?> clz = this.getClass();
		// 2得到子类对象的泛型父类类型（也就是BaseDaoImpl<T>）
		ParameterizedType type = (ParameterizedType) clz.getGenericSuperclass();
		System.out.println(type);
		Type[] types = type.getActualTypeArguments();
		Class<T> entityClass = (Class<T>) types[0];
		return entityClass;
	}
	
	public T load(ID id) throws DataAccessException {
		return (T) getHibernateTemplate().get(getModelClass(), id);
		// return (T)getHibernateTemplate().load(getModelClass(), id);
	}

	public List<T> loadByIds(ID[] ids) throws DataAccessException {
		return (List<T>) getHibernateTemplate()
				.find("FROM " + getModelClass().getSimpleName() + " obj WHERE obj.id in " + this.getString(ids));
	}
	public List<T> loadByIdAndNotDeleteFlg(ID[] ids) throws DataAccessException {
		return (List<T>) getHibernateTemplate()
				.find("FROM " + getModelClass().getSimpleName() + " obj WHERE obj.deleteFlg != 1 and obj.id in " + this.getString(ids));
	}
	public T loadByIdAndNotDeleteFlg(ID id) throws DataAccessException {
		List<T> tmp =(List<T>)getHibernateTemplate()
				.find("FROM " + getModelClass().getSimpleName() + " obj WHERE obj.deleteFlg != 1 and obj.id = " + id);
		if(tmp.size()==0){
			return null;
		}
		return tmp.get(0);
	}

	private String getString(ID[] ids) {
		String sIds = "(";
		for (int i = 0; i < ids.length; i++) {
			ID id = ids[i];
			sIds += id.toString();
			if (i != ids.length - 1) {
				sIds += ",";
			}
		}
		sIds += ")";
		return sIds;
	}
	
	private String getString(Long[] ids) {
		String sIds = "(";
		for (int i = 0; i < ids.length; i++) {
			Long id = ids[i];
			sIds += id.toString();
			if (i != ids.length - 1) {
				sIds += ",";
			}
		}
		sIds += ")";
		return sIds;
	}
	
	public Integer deleteByIds(ID[] ids) throws DataAccessException {
		return getHibernateTemplate().bulkUpdate(
				"delete FROM " + getModelClass().getSimpleName() + " obj WHERE obj.id in " + this.getString(ids));
	}
	
	public void deleteByIds(String propertyName,ID[] ids) throws DataAccessException {
		getHibernateTemplate().bulkUpdate(
				"delete FROM " + getModelClass().getSimpleName() + " obj WHERE obj."+propertyName+" in " + this.getString(ids));
	}
	
	public Integer falseDeleteByIds(ID[] ids) throws DataAccessException {
		return getHibernateTemplate().bulkUpdate(
				"update " + getModelClass().getSimpleName() + " obj set obj.deleteFlg = 1 WHERE obj.deleteFlg != 1 and obj.id in " + this.getString(ids));
	}
	
	public void deleteByPropertys(String propertyName,ID[] propertys) throws DataAccessException {
		getHibernateTemplate().bulkUpdate(
				"delete FROM " + getModelClass().getSimpleName() + " obj WHERE obj."+propertyName+" in " + this.getString(propertys));
	}
	
	public long getCountByPropertys(String propertyName,ID[] propertys) throws DataAccessException {
		String hql = "select count(*) FROM " + getModelClass().getSimpleName() + " WHERE "+propertyName+" in " + this.getString(propertys);
		Query query = this.getSessionFactory().getCurrentSession().createQuery(hql);
		return (long)query.list().get(0);
	}
	
	public List<T> loadByPropertys(String propertyName,ID[] propertys) throws DataAccessException {
		return (List<T>) getHibernateTemplate()
				.find("FROM " + getModelClass().getSimpleName() + " obj WHERE obj."+propertyName+" in " + this.getString(propertys));
	}
	
	private String getString(Object[] propertys) {
		String sPropertys = "(";
		for (int i = 0; i < propertys.length; i++) {
			String property =(String)propertys[i];
			sPropertys += property.toString();
			if (i != propertys.length - 1) {
				sPropertys += ",";
			}
		}
		sPropertys += ")";
		return sPropertys;
	}


	public int deleteByIds(ID[] ids, Long createUserId) throws DataAccessException {
		return getHibernateTemplate().bulkUpdate("delete FROM " + getModelClass().getSimpleName()
				+ " obj WHERE obj.createUserId=" + createUserId + " AND obj.id in " + this.getString(ids));
	}

	public List<T> loadAll() throws DataAccessException {
		return (List<T>) getHibernateTemplate().find("FROM " + getModelClass().getSimpleName() + " ORDER BY id");
	}

	public List<T> loadAllOrderBy(String order) throws DataAccessException {
		Criteria cri = getSessionFactory().getCurrentSession().createCriteria(getModelClass());
		cri.addOrder(Order.asc(order));
		return (List<T>) cri.list();
	}

	public List<T> loadAllDescOrderBy(String order) throws DataAccessException {
		Criteria cri = getSessionFactory().getCurrentSession().createCriteria(getModelClass());
		cri.addOrder(Order.desc(order));
		return (List<T>) cri.list();
	}

	public void delete(T t) throws DataAccessException {
		if (t != null) {
			getHibernateTemplate().delete(t);
			getHibernateTemplate().flush();
			getSessionFactory().getCurrentSession().flush();
			getSessionFactory().getCurrentSession().clear();
			// getHibernateTemplate().getSessionFactory().evictQueries();
		}
	}

	public void flush() throws DataAccessException {
		getHibernateTemplate().flush();
	}

	public void clear() throws DataAccessException {
		getHibernateTemplate().clear();
	}

	public ID save(T t) throws DataAccessException {
		if (t != null) {
			return (ID) getHibernateTemplate().save(t);
		}
		return null;
	}

	public void update(T t) throws DataAccessException {
		if (t != null) {
			getHibernateTemplate().update(t);
		}

	}

	public void merge(T t) throws DataAccessException {
		if (t != null) {
			getHibernateTemplate().merge(t);
		}
	}

	
	public Object max(String property) throws DataAccessException {
		Criteria cri = getSessionFactory().getCurrentSession().createCriteria(getModelClass());
		cri.setProjection(Projections.max(property));
		;
		return cri.uniqueResult();
	}


	public void deleteAll() throws DataAccessException {
		getHibernateTemplate().bulkUpdate("delete " + getModelClass().getSimpleName());
	}

	public void deleteAll(Collection<T> entities) throws DataAccessException {
		if (entities != null) {
			getHibernateTemplate().deleteAll(entities);
		}
	}

	public void evict(T t) throws DataAccessException {
		if (t != null)
			getHibernateTemplate().evict(t);
	}

	// public void saveOrUpdateAll(Collection<T> entities)
	// throws DataAccessException {
	// getHibernateTemplate().saveOrUpdateAll(entities);
	//
	// }

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

	@SuppressWarnings("rawtypes")
	public List<T> findAllByCriteria(final DetachedCriteria detachedCriteria) throws DataAccessException {
		return (List) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
				Criteria criteria = detachedCriteria.getExecutableCriteria(session);
				return (List<T>) criteria.list();
			}
		});
	}

	public int getCountByCriteria(final DetachedCriteria detachedCriteria) throws DataAccessException {
		Integer count = (Integer) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
				Criteria criteria = detachedCriteria.getExecutableCriteria(session);
				return criteria.setProjection(Projections.rowCount()).uniqueResult();
			}
		});
		return count.intValue();
	}
	
	public int getCount() {
		Object count = getSessionFactory().getCurrentSession().createCriteria(getModelClass())
				.setProjection(Projections.rowCount()).uniqueResult();
		if (count == null) {
			return 0;
		}
		return Integer.valueOf(count.toString());
	}

	public List<T> getListByProperty(String property, Object value) throws DataAccessException {

		String hql = String.format("from %s where %s=:val ", getModelClass().getSimpleName(), property);

		Query query = this.getSessionFactory().getCurrentSession().createQuery(hql);
		query.setParameter("val", value);
		return query.list();
	}
	
	public List<T> getListByProperty(String property, Object value,String orderProperty,String order) throws DataAccessException {

		String hql = String.format("from %s where %s=:val order by %s %s", getModelClass().getSimpleName(), property,orderProperty,order);

		Query query = this.getSessionFactory().getCurrentSession().createQuery(hql);
		query.setParameter("val", value);
		return query.list();
	}
	
	public long deleteByProperty(String property, Object value) throws DataAccessException {

		String hql = String.format("delete %s as u where u.%s = :val ", getModelClass().getSimpleName(), property);

		Query query = this.getSessionFactory().getCurrentSession().createQuery(hql);
		query.setParameter("val", value);
		
		long count = query.executeUpdate();
//		getHibernateTemplate().flush();
//		getSessionFactory().getCurrentSession().flush();
//		getSessionFactory().getCurrentSession().clear();
		return count;
	}
	
	public List<T> getAdminUserSupplierList() throws DataAccessException {
		String hql = String.format("select u from %s u ,Supplier s where u.supplier = s.id", getModelClass().getSimpleName());
		Query query = this.getSessionFactory().getCurrentSession().createQuery(hql);
		return (List<T>)query.list();
	}
	
	 // TODO HQL START
	
	public long getCount(String property1, Object value1,String property2, Object value2) throws DataAccessException {

		String hql = String.format("select count(*) from %s where %s like :val1 and %s like :val2 ", getModelClass().getSimpleName(), property1,property2);
		Query query = this.getSessionFactory().getCurrentSession().createQuery(hql);
		query.setParameter("val1", value1);
		query.setParameter("val2", value2);
		return (long)query.list().get(0);
	}
	
	public long getCount(String property1, Object value1) throws DataAccessException {

		String hql = String.format("select count(*) from %s where %s = :val1", getModelClass().getSimpleName(), property1);
		Query query = this.getSessionFactory().getCurrentSession().createQuery(hql);
		query.setParameter("val1", value1);
		return (long)query.list().get(0);
	}
	
	public List<T> getOffsetLimitOrderListByPropertys(String property1, Object value1,String property2, Object value2, String orderProperty,String order, int limit, int offset) throws DataAccessException {

		String hql = String.format("from %s where %s like :val1 and %s like :val2 order by %s %s", getModelClass().getSimpleName(), property1,property2,orderProperty,order);

		Query query = this.getSessionFactory().getCurrentSession().createQuery(hql);
		query.setParameter("val1", value1);
		query.setParameter("val2", value2);
		query.setFirstResult(offset);
		query.setMaxResults(limit);
		return query.list();
	}
	
	public List<T> getOrderListByPropertys(String property1, Object value1,String property2, Object value2, String orderProperty,String order) throws DataAccessException {

		String hql = String.format("from %s where %s like :val1 and %s like :val2 order by %s %s", getModelClass().getSimpleName(), property1,property2,orderProperty,order);

		Query query = this.getSessionFactory().getCurrentSession().createQuery(hql);
		query.setParameter("val1", value1);
		query.setParameter("val2", value2);
		return query.list();
	}
	
	public List<T> getOffsetLimitOrderList(String orderProperty,String order, int limit, int offset) throws DataAccessException {

		String hql = String.format("from %s order by %s %s", getModelClass().getSimpleName(),orderProperty,order);
		Query query = this.getSessionFactory().getCurrentSession().createQuery(hql);
		query.setFirstResult(offset);
		query.setMaxResults(limit);
		return query.list();
	}
	
	public List<T> getOffsetLimitOrderListByProperty(String property, Object value,String orderProperty,String order, int limit, int offset) throws DataAccessException {
		String hql = String.format("from %s where %s = :val order by %s %s", getModelClass().getSimpleName(), property,orderProperty,order);
		Query query = this.getSessionFactory().getCurrentSession().createQuery(hql);
		query.setParameter("val", value);
		query.setFirstResult(offset);
		query.setMaxResults(limit);
		return query.list();
	}
	

	public List<T> getAllByCondition(String property) throws DataAccessException {

		String hql = String.format("from %s where %s", getModelClass().getSimpleName(), property);

		Query query = this.getSessionFactory().getCurrentSession().createQuery(hql);

		return query.list();
	}
	
	
	 // TODO HQL END

	public long getLastInsertId() throws DataAccessException {

		String hql = String.format("select max(a.id) from %s a", getModelClass().getSimpleName());

		long maxid = new Long((getSessionFactory().getCurrentSession().createQuery(hql).uniqueResult()).toString());

		return maxid;
	}
	
	
	
	
	//TODO HQL START
	
	
	//getCount by map
	public Long getCountByFields(Map<String,Object> FieldsMap,Boolean like) throws DataAccessException {
		String tmpSqlFromMap = getStringFromMap(FieldsMap,like);
		String modelName = getModelClass().getSimpleName();
		String hql = String.format("select count(*) from %s where 1=1 %s", modelName, tmpSqlFromMap);
		Query query = getQueryFromMapAndHql(FieldsMap,hql);
		return (Long)query.list().get(0);
	}
	//getSomething by map
	
	public List<T> getOffsetLimitOrderListByFields(Map<String,Object> FieldsMap,String orderProperty,String order, int limit, int offset,Boolean like) throws DataAccessException {
		Query query = getQueryFromMap(FieldsMap,like,true);
		query.setParameter("orderProperty", orderProperty);
		query.setParameter("order", order);
		query.setFirstResult(offset);
		query.setMaxResults(limit);
		return query.list();
	}
	
	public List<T> getOrderListByFields(Map<String,Object> FieldsMap,String orderProperty,String order,Boolean like) throws DataAccessException {
		Query query = getQueryFromMap(FieldsMap,like,true);
		query.setParameter("orderProperty", orderProperty);
		query.setParameter("order", order);
		return query.list();
	}
	
	public List<T> getOrderListByFields(Map<String,Object> FieldsMap,Boolean like) throws DataAccessException {
		Query query = getQueryFromMap(FieldsMap,like,false);
		return query.list();
	}
	
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
	
	public Query getQueryFromMapAndHql(Map<String,Object> FieldsMap,String hql){
		Query query = this.getSessionFactory().getCurrentSession().createQuery(hql);
		for(Map.Entry<String, Object> entry : FieldsMap.entrySet()){
			query.setParameter(entry.getKey(), entry.getValue());
		}
		return query;
	}
	public Query getQueryFromMapAndHql(Map<String,String> FieldsMap,Query query){
		for(Map.Entry<String, String> entry : FieldsMap.entrySet()){
			query.setParameter(entry.getKey(), entry.getValue()+"");
		}
		return query;
	}
	
	//TODO HQL END
	
	@Autowired  
    public void setSessionFactoryOverride(SessionFactory sessionFactory)   
    {   
        super.setSessionFactory(sessionFactory);   
    }
	
	public int executeUpdateBySql(String updateSql, Object[] paramArray) {
		Query query = this.getSessionFactory().getCurrentSession().createSQLQuery(updateSql);
		if (paramArray!=null) {
			for (int i=0; i< paramArray.length; i++) {
				query.setParameter(i, paramArray[i]);
			}
		}
		return query.executeUpdate();
	}
	
	public Session getSession(){
		return this.getSessionFactory().openSession();
	}
	
}

package com.lx.daos;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.dao.DataAccessException;


/**
 * @author liuxin
 *
 * @param <T>
 * @param <ID>
 */
public interface Dao<T, ID extends Serializable> {
	
	public Session openSession();
	
	public Session getSession();
	
	//利用反射来获取调用此方法的类
	
	public Class<T> getModelClass() throws DataAccessException;
	
	public void flush() throws DataAccessException;
	
	public void refresh(T t) throws DataAccessException;
	
	public void clear() throws DataAccessException;
	
	//获取数据的方法
	
	public T get(ID id) throws DataAccessException;
	
	public List<T> find(String sql) throws DataAccessException;
	
	public List<T> findByIds(ID[] ids) throws DataAccessException;
	
	public List<T> findByInValues(String propertyName,Object[] values) throws DataAccessException;
	
	public List<T> findAll() throws DataAccessException;
	
	public List<T> findAllOrderBy(String order) throws DataAccessException;
	
	public List<T> findAllDescOrderBy(String order) throws DataAccessException;
	
	public List<T> getListByProperty(String property, Object value) throws DataAccessException;
	
	public List<T> getOrderListByProperty(String property, Object value,String orderProperty,String order) throws DataAccessException;
	
	public List<T> getOffsetLimitOrderList(String orderProperty,String order, int limit, int offset) throws DataAccessException;
	
	public List<T> getOffsetLimitOrderListByProperty(String property, Object value,String orderProperty,String order, int limit, int offset) throws DataAccessException;
	
	public List<T> getAllByHql(String hql,Object[] paramArray) throws DataAccessException;
	
	
	public List<T> findTopByCriteria(final DetachedCriteria detachedCriteria, final int top, final Order[] orders)
			throws DataAccessException;
	
	public List<T> findAllByCriteria(final DetachedCriteria detachedCriteria) throws DataAccessException;

	//获取数量
	public long getCount() throws DataAccessException;
	
	public long getCount(String property, Object value) throws DataAccessException;
	
	public long getCountByPropertys(String propertyName,Object[] propertys) throws DataAccessException;
	
	public long getCountByHql(String hql) throws DataAccessException;
	
	public long getCountBySql(String sql) throws DataAccessException;
	
	public int getCountByCriteria(final DetachedCriteria detachedCriteria) throws DataAccessException;
	
	public int getUniqueCount();
	
	//删除数据的方法
	
	public void delete(T t) throws DataAccessException;
	
	public Integer deleteByIds(ID[] ids) throws DataAccessException;
	
	public long deleteByProperty(String property, Object value) throws DataAccessException;
	
	public void deleteByInValues(String propertyName,Object[] values) throws DataAccessException;
	
	public void deleteAll() throws DataAccessException;
	
	public void deleteAll(Collection<T> entities) throws DataAccessException;

	//执行数据保存的方法
	
	public ID save(T t) throws DataAccessException;
	
	// 执行数据更新的方法
	
	public void update(T t) throws DataAccessException;
	
	public Integer bulkUpdate(String sql);
	
	public void merge(T t) throws DataAccessException;
	
	public void evict(T t) throws DataAccessException;
	
	public Object max(String property) throws DataAccessException;
	
	public long getLastInsertId() throws DataAccessException;
	
	//getSomething by map
	
	public Long getCountByFields(Map<String,Object> FieldsMap,Boolean like) throws DataAccessException;
	
	public List<T> getOffsetLimitOrderListByFields(Map<String,Object> FieldsMap,String orderProperty,String order, int limit, int offset,Boolean like) throws DataAccessException;
	
	public List<T> getOrderListByFields(Map<String,Object> FieldsMap,String orderProperty,String order,Boolean like) throws DataAccessException;
	
	public List<T> getOrderListByFields(Map<String,Object> FieldsMap,Boolean like) throws DataAccessException;
	
	public Query getQueryFromMap(Map<String,Object> FieldsMap,Boolean like,Boolean order);
	
	public String getStringFromMap(Map<String,Object> FieldsMap,Boolean like);
	
	public Query getQueryFromMapAndHql(Map<String,Object> FieldsMap,String hql);
	
	public Query getQueryFromMapAndHql(Map<String,String> FieldsMap,Query query);
	
	public int executeUpdateBySql(String updateSql, Object[] paramArray);
	
	public Query getQueryByHqlAndObjects(String hql,Object[] paramArray);
	
	public Query getQueryBySqlAndObjects(String sql,Object[] paramArray);
	
	public void setQueryByParamArray(Query query,Object[] paramArray);
}

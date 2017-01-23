package com.lx.daos;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

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
	
	/**
	 * 
	 * @return
	 * @throws DataAccessException
	 */
	Class<T> getModelClass() throws DataAccessException;
	
	
	/**
	 * @param id
	 * @return
	 * @throws DataAccessException
	 */
	T load(ID id) throws DataAccessException;

	/**
	 * 
	 * @param ids
	 * @return
	 * @throws DataAccessException
	 */
	List<T> loadByIds(ID[] ids) throws DataAccessException;

	/**
	 * 
	 * @param ids
	 * @param createUserId
	 * @return
	 * @throws DataAccessException
	 */
	int deleteByIds(ID[] ids, Long createUserId) throws DataAccessException;

	/**
	 * 
	 * @param ids
	 * @throws DataAccessException
	 */
	Integer deleteByIds(ID[] ids) throws DataAccessException;

	/**
	 * 
	 * @return
	 * @throws DBDaoException
	 */
	List<T> loadAll() throws DataAccessException;

	/**
	 * 
	 * @param order
	 * @return
	 * @throws DataAccessException
	 */
	List<T> loadAllOrderBy(String order) throws DataAccessException;

	/**
	 * 
	 * @param order
	 * @return
	 * @throws DataAccessException
	 */
	List<T> loadAllDescOrderBy(String order) throws DataAccessException;

	/**
	 * insert a record
	 * 
	 * @param t
	 * @throws DBDaoException
	 */
	ID save(T t) throws DataAccessException;

	/**
	 * update a record
	 * 
	 * @param t
	 * @throws DataAccessException
	 */
	void update(T t) throws DataAccessException;

	/**
	 * delete a record
	 * 
	 * @param t
	 * @throws DBDaoException
	 */
	void delete(T t) throws DataAccessException;

	/**
	 * flush
	 * 
	 * @throws DBDaoException
	 */
	void flush() throws DataAccessException;

	/**
	 * clear
	 * 
	 * @throws DataAccessException
	 */
	void clear() throws DataAccessException;

	/**
	 * 
	 * @param property
	 * @return
	 */
	Object max(String property) throws DataAccessException;

	/**
	 * 
	 * @param t
	 * @throws DataAccessException
	 */
	void evict(T t) throws DataAccessException;

	/**
	 * 
	 * @param t
	 * @throws DataAccessException
	 */
	void merge(T t) throws DataAccessException;

	/**
	 * 
	 * @throws DataAccessException
	 */
	void deleteAll() throws DataAccessException;

	/**
	 * 
	 * 
	 * @param entities
	 * @throws DataAccessException
	 */
	void deleteAll(Collection<T> entities) throws DataAccessException;

	/**
	 * 
	 * @param collection
	 * @throws DataAccessException
	 */
	
	// TODO HQL START
	
	// void saveOrUpdateAll(Collection<T> entities) throws DataAccessException;

	
	/**
	 * @return
	 */
	int getCount();

	
	/**
	 * @param property
	 * @param value
	 * @return
	 */
	List<T> getListByProperty(String property, Object value);

	long getLastInsertId() throws DataAccessException;

	List<T> findTopByCriteria(final DetachedCriteria detachedCriteria, final int top, final Order[] orders)
			throws DataAccessException;

	List<T> getOffsetLimitOrderListByProperty(String property, Object value, String orderProperty, String order,
			int limit, int offset) throws DataAccessException;
	
	List<T> getAllByCondition(String property) throws DataAccessException;
	
	List<T> getOffsetLimitOrderListByPropertys(String property1, Object value1,String property2,
			Object value2, String orderProperty,String order, 
			int limit, int offset) throws DataAccessException;
	
	long getCount(String property1, Object value1,String property2, Object value2) throws DataAccessException;	
	int getCountByCriteria(final DetachedCriteria detachedCriteria) throws DataAccessException;
	public long getCount(String property1, Object value1) throws DataAccessException;
	public long deleteByProperty(String property, Object value) throws DataAccessException;
	public void deleteByPropertys(String propertyName,ID[] propertys) throws DataAccessException;
	public void deleteByIds(String propertyName,ID[] ids) throws DataAccessException;
	public List<T> getOffsetLimitOrderList(String orderProperty,String order, int limit, int offset) throws DataAccessException;
	public List<T> getOrderListByPropertys(String property1, Object value1,String property2, Object value2, String orderProperty,String order) throws DataAccessException;
	public List<T> getListByProperty(String property, Object value,String orderProperty,String order) throws DataAccessException;
	
	public int executeUpdateBySql(String updateSql, Object[] paramArray);
	public Integer falseDeleteByIds(ID[] ids) throws DataAccessException;
	public List<T> loadByIdAndNotDeleteFlg(ID[] ids) throws DataAccessException;
	public T loadByIdAndNotDeleteFlg(ID id) throws DataAccessException;
	// TODO HQL END
}

package com.lx.services;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.service.spi.ServiceException;

public interface Service<T, ID extends Serializable> {
	/**
	 * 
	 * @return
	 * @throws ServiceException
	 */
	abstract Class<T> getModelClass() throws ServiceException;
	/**
	 * 
	 * @param id
	 * @return
	 * @throws ServiceException
	 */
	T findById(ID id) throws ServiceException;
	
	/**
	 * 
	 * @param t
	 * @throws ServiceException
	 */
	ID save(T t) throws ServiceException;
	
	/**
	 * 
	 * @param t
	 * @throws ServiceException
	 */
	void update(T t) throws ServiceException;
	
	/**
	 * 
	 * @param id
	 * @throws ServiceException
	 */
	void deleteById(ID id) throws ServiceException;
	
	/**
	 * 
	 * @param ids
	 */
	void deleteByIds(ID[] ids) throws ServiceException;
	
	
	/**
	 * delete all data from table
	 * 
	 * @throws ServiceException
	 */
	void deleteAll() throws ServiceException;
	
	/**
	 * load all data from table
	 * 
	 * @return
	 * @throws ServiceException
	 */
	List<T> loadAll() throws ServiceException;
	
	/**
	 * load all data from table order by "order"
	 * 
	 * @param order
	 * @return
	 * @throws ServiceException
	 */
	List<T> loadAllOrderBy(String order) throws ServiceException;

	/**
	 * load all data from table order by "order" desc
	 * 
	 * @param order
	 * @return
	 * @throws ServiceException
	 */
	List<T> loadAllDescOrderBy(String order) throws ServiceException;
	
	long getLastInsertId() throws ServiceException;
	
	List<T> getListByProperty(String property, Object value);
	
	int getCount();
	
	// TODO HQL START
	List<T> getOffsetLimitOrderListByProperty(String property, Object value, String orderProperty, String order,
			int limit, int offset) throws ServiceException;
	
	List<T> getAllByCondition(String property) throws ServiceException;
	
	List<T> findTopByCriteria(final DetachedCriteria detachedCriteria, final int top, final Order[] orders)
			throws ServiceException;
	
	List<T> getOffsetLimitOrderListByPropertys(String property1, Object value1,String property2,
			Object value2, String orderProperty,String order, 
			int limit, int offset) throws ServiceException;
	
	long getCount(String property1, Object value1,String property2, Object value2) throws ServiceException;
	
	int getCountByCriteria(final DetachedCriteria detachedCriteria) throws ServiceException;
	Object max(String property) throws ServiceException;
	List<T> getOffsetLimitOrderList(String orderProperty,String order, int limit, int offset) throws ServiceException;
	List<T> loadByIdAndNotDeleteFlg(ID[] ids) throws ServiceException;
	T loadByIdAndNotDeleteFlg(ID id) throws ServiceException;
	// TODO HQL END
}

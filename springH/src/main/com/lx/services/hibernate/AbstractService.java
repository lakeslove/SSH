package com.lx.services.hibernate;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.lx.daos.Dao;
import com.lx.services.Service;

public abstract class AbstractService<T, ID extends Serializable> implements Service<T, ID> {
	/**
	 * 
	 * @return
	 * @throws ServiceException
	 */
	@Autowired
	protected MessageSource messages;
	
	public abstract Dao<T, ID> getDao() throws ServiceException;
	
	protected HttpServletRequest getRequest() {
		RequestAttributes ra = RequestContextHolder.getRequestAttributes();
		return ((ServletRequestAttributes)ra).getRequest();
	}
	
	protected HttpSession getSession() {
		HttpServletRequest request = getRequest();
		return request.getSession();
	}
	
	public String getI18nMessage(String code) {
		Locale locale = LocaleContextHolder.getLocale();
		return messages.getMessage(code, null, locale);
	}
	
	public  Class<T> getModelClass() throws ServiceException {
		return getDao().getModelClass();
	}

	@Transactional(readOnly=true, rollbackFor=ServiceException.class)
	public T findById(ID id) throws ServiceException {
		return getDao().get(id);
	}

	@Transactional(rollbackFor=ServiceException.class)
	public void deleteById(ID id) throws ServiceException {
		T t = getDao().get(id);
		getDao().delete(t);
	}

	@Transactional(rollbackFor=ServiceException.class)
	public ID save(T t) throws ServiceException {
		return getDao().save(t);
	}

	@Transactional(rollbackFor=ServiceException.class)
	public void update(T t) throws ServiceException {
		getDao().update(t);
		
	}
	
	
	@Transactional(readOnly=true, rollbackFor=ServiceException.class)
	public List<T> loadByProperty(String property, Object value)
			throws ServiceException {
		return getDao().getListByProperty(property, value);
	}
	
	
	@Transactional(readOnly=true, rollbackFor=ServiceException.class)
	public List<T> loadByProperty(Map<String, Object> map) throws ServiceException {
		return getDao().getListByProperty(null, map);
	}
	
	@Transactional(rollbackFor=ServiceException.class)
	public void deleteAll() throws ServiceException {
		getDao().deleteAll();
	}
	
	@Transactional(rollbackFor=ServiceException.class)
	public void deleteByIds(ID[] ids) throws ServiceException {
		// List<T> entities = getDao().loadByIds(ids);
		// getDao().deleteAll(entities);
		getDao().deleteByIds(ids);
	}
	
	@Transactional(readOnly=true, rollbackFor=ServiceException.class)
	public List<T> loadAll() throws ServiceException {
		return getDao().findAll();
	}
	
	@Transactional(readOnly=true, rollbackFor=ServiceException.class)
	public List<T> loadAllOrderBy(String order) throws ServiceException {
		return getDao().findAllOrderBy(order);
	}

	@Transactional(readOnly=true, rollbackFor=ServiceException.class)
	public List<T> loadAllDescOrderBy(String order) throws ServiceException {
		return getDao().findAllDescOrderBy(order);
	}
	
	@Transactional(readOnly=true, rollbackFor=ServiceException.class)
	public long getLastInsertId() throws ServiceException {
		return getDao().getLastInsertId();
	}
	
	@Transactional(readOnly=true, rollbackFor=ServiceException.class)
	public int getCount() throws ServiceException {
		return getDao().getUniqueCount();
	}
	
	// TODO HQL START
	
	@Transactional(readOnly=true, rollbackFor=ServiceException.class)
	public List<T> getOffsetLimitOrderListByProperty(String property, Object value, String orderProperty, String order,
			int limit, int offset) throws ServiceException{
		return getDao().getOffsetLimitOrderListByProperty(property, value, orderProperty, order,
				limit, offset);
	}
	
	@Transactional(readOnly=true, rollbackFor=ServiceException.class)
	public List<T> findTopByCriteria(final DetachedCriteria detachedCriteria, final int top, final Order[] orders)
			throws ServiceException{
		return getDao().findTopByCriteria(detachedCriteria, top, orders);
	}
	
	
	@Transactional(readOnly=true, rollbackFor=ServiceException.class)
	public int getCountByCriteria(final DetachedCriteria detachedCriteria) throws ServiceException{
		return getDao().getCountByCriteria(detachedCriteria);
	}
	
	@Transactional(readOnly=true, rollbackFor=ServiceException.class)
	public List<T> getListByProperty(String property, Object value) throws ServiceException{
		return getDao().getListByProperty(property, value);
	}
	
	@Transactional(readOnly=true, rollbackFor=ServiceException.class)
	public Object max(String property) throws ServiceException{
		return getDao().max(property);
	}
	
	@Transactional(readOnly=true, rollbackFor=ServiceException.class)
	public List<T> getOffsetLimitOrderList(String orderProperty,String order, int limit, int offset) throws ServiceException{
		return getDao().getOffsetLimitOrderList(orderProperty,order, limit, offset);
	}
	
	// TODO HQL END
}

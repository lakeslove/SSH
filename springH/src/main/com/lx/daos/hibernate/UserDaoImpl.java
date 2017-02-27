package com.lx.daos.hibernate;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.lx.daos.UserDao;
import com.lx.models.User;

@Repository
public class UserDaoImpl extends AbstractDao<User, Long> implements UserDao {
	
	private static final Logger log = LogManager.getLogger(UserDaoImpl.class);

	@Override
	public List<User> getUserListBySQL(Map<String, Object> condition) {
		// TODO Auto-generated method stub
		String sql = "select * from user where id < 10";
		Query query = getSession().createSQLQuery(sql);
		return query.list();
	}

	@Override
	public List<User> getUserListByHQL(Map<String, Object> condition) {
		String hql = "select User from User where id < 10";
		Query query = getSession().createQuery(hql);
		return query.list();
	}

//	@Override
//	public Class<User> getModelClass() throws DataAccessException {	
//		return User.class;
//	}

}
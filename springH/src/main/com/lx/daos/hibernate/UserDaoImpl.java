package com.lx.daos.hibernate;

import com.lx.daos.UserDao;
import com.lx.models.User;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl extends AbstractDao<User, Long> implements UserDao {
	
	private static final Logger log = LogManager.getLogger(UserDaoImpl.class);

//	@Override
//	public Class<User> getModelClass() throws DataAccessException {	
//		return User.class;
//	}

}
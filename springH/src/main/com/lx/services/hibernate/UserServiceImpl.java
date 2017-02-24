package com.lx.services.hibernate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lx.daos.Dao;
import com.lx.daos.UserDao;
import com.lx.models.User;
import com.lx.services.UserService;
import com.lx.utils.PageData;
@Service
public class UserServiceImpl extends AbstractService<User, Long>implements UserService {

	private static final Logger log = LogManager.getLogger(UserServiceImpl.class);

	@Autowired
	private UserDao userDao;
	
	@Override
	public Dao<User, Long> getDao() {
		return userDao;
	}

	@Override
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public PageData<User> serchUsers(User user, int currentPage) {
		Map<String,Object> FieldsMap = new HashMap<>();
		FieldsMap.put("name", user.getName());
		FieldsMap.put("slogan", user.getSlogan());
		Long sizeOfAll = userDao.getCountByFields(FieldsMap, true);
		List<User> userList = userDao.getOffsetLimitOrderListByFields(FieldsMap, "id", "asc", PageData.getPerPageNum(), PageData.getOffset(currentPage), true);
		PageData<User> pageData = new PageData<User>(sizeOfAll,userList);
		return pageData;
	}

//	@Transactional(readOnly=false, rollbackFor=Exception.class)
//	public String searchPostAreaCode(String postAreaCode,String postAreaName,int page) throws Exception{
//		
//		return dataHelp.getJsonString();
//	}
}
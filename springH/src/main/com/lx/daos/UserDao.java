package com.lx.daos;

import java.util.List;
import java.util.Map;

import com.lx.models.User;

public interface UserDao extends Dao<User, Long>{
	public List<User> getUserListBySQL(Map<String,Object> condition);
	public List<User> getUserListByHQL(Map<String,Object> condition);
}

package com.lx.services;

import com.lx.models.User;
import com.lx.utils.PageData;

public interface UserService extends Service<User, Long>{
	public PageData<User> serchUsers(User user,Long currentPage);
}

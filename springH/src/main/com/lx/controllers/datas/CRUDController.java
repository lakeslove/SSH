package com.lx.controllers.datas;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lx.controllers.AbstractController;
import com.lx.models.User;
import com.lx.services.UserService;
import com.lx.utils.JSONUtil;
import com.lx.utils.PageData;

@Controller
public class CRUDController {
	private static final Logger log = LoggerFactory.getLogger(AbstractController.class);
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = { "crud.htm" })
	public String crud() {
		return "tiles.view.body.crud";
	}
	
	@RequestMapping(value = { "searchUsers.htm" })
	public String searchUsers(@ModelAttribute User user,Integer currentPage) throws JsonGenerationException, JsonMappingException, IOException {
		PageData<User> pageData = userService.serchUsers(user,currentPage);
		return JSONUtil.getEscapeJSONString(pageData);
	}
	
	@RequestMapping(value = { "addUser.htm" })
	public String addUser(@ModelAttribute User user,Integer currentPage) throws JsonGenerationException, JsonMappingException, IOException {
		return "tiles.view.body.addUser";
	}
	
	@RequestMapping(value = { "editUser.htm" })
	public String editUser(@ModelAttribute User user,Integer currentPage) throws JsonGenerationException, JsonMappingException, IOException {
		PageData<User> pageData = userService.serchUsers(user,currentPage);
		return "tiles.view.body.editUser";
	}
	
	@RequestMapping(value = { "saveUser.htm" })
	public String saveUser(@ModelAttribute User user,Integer currentPage) throws JsonGenerationException, JsonMappingException, IOException {
		PageData<User> pageData = userService.serchUsers(user,currentPage);
		return JSONUtil.getEscapeJSONString(pageData);
	}
}

package com.lx.controllers.datas;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lx.controllers.AbstractController;
import com.lx.models.User;
import com.lx.services.UserService;
import com.lx.utils.JSONUtil;
import com.lx.utils.PageData;

@Controller
public class CRUDController extends AbstractController{
	private static final Logger log = LoggerFactory.getLogger(AbstractController.class);
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = { "crud.htm" })
	public String crud() {
		return "tiles.view.body.userList";
	}
	
	@ResponseBody
	@RequestMapping(value = { "searchUsers.htm" },method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
	public String searchUsers(@ModelAttribute User user,Integer currentPage) throws JsonGenerationException, JsonMappingException, IOException {
		PageData<User> pageData = userService.serchUsers(user,currentPage);
		return JSONUtil.getEscapeJSONString(pageData);
	}
	
	@RequestMapping(value = { "newUser.htm" })
	public String newUser(@ModelAttribute User user) throws JsonGenerationException, JsonMappingException, IOException {
		return "tiles.view.body.userMessage";
	}
	
	@RequestMapping(value = { "editUser.htm" })
	public String editUser(Model model,Long userId) throws JsonGenerationException, JsonMappingException, IOException {
		User user = userService.findById(userId);
		model.addAttribute("user", user);
		return "tiles.view.body.userMessage";
	}
	
	@RequestMapping(value = { "saveUser.htm" })
	public String saveUser(@ModelAttribute User user) throws JsonGenerationException, JsonMappingException, IOException {
		if(user.getId()!=null){
			userService.update(user);
		}else{
			userService.save(user);
		}
//		return "redirect:editUser.htm?userId="+user.getId();
		return "redirect:crud.htm";
	}
	
	@RequestMapping(value = { "deleteUser.htm" })
	public String saveUser(Long userId) throws JsonGenerationException, JsonMappingException, IOException {
		if(userId!=null){
			userService.deleteById(userId);;
		}
//		return "redirect:editUser.htm?userId="+user.getId();
		return "redirect:crud.htm";
	}
}

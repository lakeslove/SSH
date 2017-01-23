package com.lx.controllers.publics;

import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lx.controllers.AbstractController;
import com.lx.models.User;
import com.lx.services.UserService;

@Controller
public class JavaController extends AbstractController{

	private static final Logger log = LogManager.getLogger(JavaController.class);
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = { "java.htm" })
	public String java(Model model) {
		log.info("java Start");
		User user = userService.findById(1l);
		System.out.println(userService.getModelClass());
		model.addAttribute("userName", user.getName());
		log.info("java end" + user.getName());
		return "tiles.view.body.java";
	}
	
	@RequestMapping(value = { "mysql.htm" })
	public String mysql(Model model) {
		log.info("java Start");
		User user = userService.findById(1l);
		System.out.println(userService.getModelClass());
		model.addAttribute("userName", user.getName());
		log.info("java end" + user.getName());
		return "tiles.view.body.mysql";
	}
	
//	@RequestMapping(value = { "save.htm" })
//	public String save(Map testMap,Model model) {
//		log.info("java Start");
		
//		if (user.getId()==null) {
//			bindResult.rejectValue("id","404", "idError");
//		}
//		if (user.getName()==null) {
//			bindResult.rejectValue("name","", "nameError");
//		}
//		if (user.getAge()==null) {
//			bindResult.rejectValue("age","", "ageError");
//		}
//		model.addAttribute("user", user);
//		log.info("java end" + user.getName());
//		return "tiles.view.body.java";
//	}
	
	@RequestMapping(value = { "save.htm" })
	public String save(@Valid User user,BindingResult bindResult,Model model) {
		log.info("java Start");
		if(bindResult.hasFieldErrors()){
			List<ObjectError> objError = bindResult.getAllErrors();
			System.out.println(objError);
		}
		if (user.getId()==null) {
			bindResult.rejectValue("id","404", "idError");
		}
		if (user.getName()==null) {
			bindResult.rejectValue("name","", "nameError");
		}
		if (user.getAge()==null) {
			bindResult.rejectValue("age","", "ageError");
		}
		user.setId(58L);
		model.addAttribute("user", user);
		log.info("java end" + user.getName());
		return "tiles.view.body.java";
	}
	
	@RequestMapping(value = { "formtest.htm" })
	public String formtest(Model model) throws Exception {
		log.info("java Start");
		User user = userService.findById(1l);
		
		user.setName("qb\r\n123");
		
		model.addAttribute("user", getJsonString(user));
		log.info("java end");
		return "tiles.view.body.java";
	}
	
	public static String getJsonString(Object data) throws Exception {
		ObjectMapper objm = new ObjectMapper();
		String supplierJson = objm.writeValueAsString(data);
		return supplierJson;
	}
	
}

package com.lx.controllers.publics;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lx.controllers.AbstractController;
import com.lx.utils.Email;
import com.lx.utils.PropertyUtil;
import com.lx.utils.SpringEmailUtil;
import com.lx.utils.VelocitiesUtil;

@Controller
public class TestController {
	
	private static final Logger log = LoggerFactory.getLogger(AbstractController.class);
	
	@RequestMapping(value = { "email.htm" })
	public String email(Model model) {
		Email email = new Email();	
		email.setFromEmailAddress(PropertyUtil.getPropertyValue("mail.fromAddress"));
		email.setFromPersonName(PropertyUtil.getPropertyValue("mail.fromAddress"));
		email.setToEmailAddresses(new String[]{"sendToxxx@qq.com"});
		email.setSubject("邮件标题");
		Map<String,Object> velocityContext = new HashMap<>();
		velocityContext.put("name", "VelocityEngine");
		velocityContext.put("result", "效果还行吧");
		String content = VelocitiesUtil.getVelocityText("email_test.vm", velocityContext);
		email.setContent(content);
		model.addAttribute("email", email);
		return "email";
	}
	
	@RequestMapping(value = { "sendEmail.htm" },method=RequestMethod.POST)
	public String sendEmail(@ModelAttribute Email email,Model model) {
//		model.addAttribute("email", email);
		email.setToEmailAddresses(email.getToEmailAddresses()[0].split(","));
		try {
			SpringEmailUtil.sendEmail(email);
			model.addAttribute("result","发送邮件成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.addAttribute("result", e.getMessage());
		}
		return "email";
	}
	
	@RequestMapping(value = { "test2.htm" })
	public String test() {
//		System.out.println(TestError.getTestString());
		return "index";
	}
	
	@RequestMapping(value = { "testError.htm" })
	public String error(Exception error,HttpServletRequest request,HttpServletResponse response) throws IOException {
		error.printStackTrace();
//		System.out.println(Arrays.toString(error.getStackTrace()));
		return "tiles.view.body.testError";
	}
	
	@ExceptionHandler(Throwable.class)
	public void handelThrowable(Throwable throwable){
		throwable.printStackTrace();
	}
	@ExceptionHandler(Exception.class)
	public void handelException(Exception exception){
		exception.printStackTrace();
	}

}

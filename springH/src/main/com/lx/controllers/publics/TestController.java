package com.lx.controllers.publics;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lx.test.TestError;


@Controller
public class TestController {
	
	@RequestMapping(value = { "test.htm" })
	public String test() {
		System.out.println(TestError.getTestString());
		return "tiles.view.body.testError";
		
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

package com.lx.controllers;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class AbstractController {
	private static final Logger log = LoggerFactory.getLogger(AbstractController.class);

	@Autowired
	protected MessageSource messages;

	public String getI18nMessage(String code) {
		Locale locale = LocaleContextHolder.getLocale();
		return messages.getMessage(code, null, locale);
	}

	public String getI18nMessage(String code, Object[] args, String defaultMessage) {
		Locale locale = LocaleContextHolder.getLocale();
		return messages.getMessage(code, args, defaultMessage, locale);
	}

	public String getI18nMessage(String code, Object[] args) {
		Locale locale = LocaleContextHolder.getLocale();
		return messages.getMessage(code, args, locale);
	}

	public String getI18nMessage(String code, Object[] args, String defaultMessage, Locale locale) {
		return messages.getMessage(code, args, defaultMessage, locale);
	}

	public String getI18nMessage(String code, Object[] args, Locale locale) {
		return messages.getMessage(code, args, locale);
	}

	public String getI18nMessage(String code, Locale locale) {
		return messages.getMessage(code, null, locale);
	}

	// 获取request
	protected HttpServletRequest getRequest() {
		RequestAttributes ra = RequestContextHolder.getRequestAttributes();
		return ((ServletRequestAttributes) ra).getRequest();
	}

	// 获取response,这个方法有问题,暂时保留
//	protected HttpServletResponse getResponse() {
//		RequestAttributes ra = RequestContextHolder.getRequestAttributes();
//		return ((ServletRequestAttributes) ra).getResponse();
//	}

	// 获取session
	protected HttpSession getSession() {
		HttpServletRequest request = getRequest();
		return request.getSession();
	}
	
	@ModelAttribute
	public void preMethod(Model model,HttpServletRequest request,HttpServletResponse response){
		
	}
	
}

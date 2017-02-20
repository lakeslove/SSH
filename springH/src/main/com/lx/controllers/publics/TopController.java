package com.lx.controllers.publics;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lx.controllers.AbstractController;

@Controller
public class TopController {
	private static final Logger log = LoggerFactory.getLogger(AbstractController.class);
	@RequestMapping(value = { "index.htm" })
	public String error() {
		return "index";
	}

}

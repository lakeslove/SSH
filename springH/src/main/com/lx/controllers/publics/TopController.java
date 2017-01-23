package com.lx.controllers.publics;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TopController {
	@RequestMapping(value = { "index.htm" })
	public String error() {
		return "index";
	}

}

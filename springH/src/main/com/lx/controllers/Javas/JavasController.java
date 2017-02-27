package com.lx.controllers.Javas;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lx.controllers.AbstractController;

@Controller
public class JavasController {
	private static final Logger log = LoggerFactory.getLogger(AbstractController.class);
	@RequestMapping(value = { "javas.htm" })
	public String index() {
		return "tiles.view.body.javas";
	}

}

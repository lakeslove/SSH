package com.lx.controllers.others;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lx.controllers.AbstractController;

@Controller
public class DemosController {
	private static final Logger log = LoggerFactory.getLogger(AbstractController.class);
	@RequestMapping(value = { "pictureShut.htm" })
	public String pictureShut() {
		return "tiles.view.body.pictureShut";
	}
	@RequestMapping(value = { "animate.htm" })
	public String animate() {
		return "tiles.view.body.animate";
	}
	@RequestMapping(value = { "orcCanvas.htm" })
	public String orcCanvas() {
		return "tiles.view.body.orcCanvas";
	}
	@RequestMapping(value = { "calendar.htm" })
	public String calendar() {
		return "tiles.view.body.calendar";
	}
	@RequestMapping(value = { "calculator.htm" })
	public String calculator() {
		return "tiles.view.body.calculator";
	}
	
	
}

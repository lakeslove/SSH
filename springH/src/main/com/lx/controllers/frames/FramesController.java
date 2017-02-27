package com.lx.controllers.frames;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lx.controllers.AbstractController;

@Controller
public class FramesController {
	private static final Logger log = LoggerFactory.getLogger(AbstractController.class);
	@RequestMapping(value = { "frames.htm" })
	public String frames() {
		return "tiles.view.body.frames";
	}
}

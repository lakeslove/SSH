package com.lx.controllers.files;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lx.controllers.AbstractController;
import com.lx.services.UserService;

@Controller
public class FileDownloadController extends AbstractController{
	private static final Logger log = LoggerFactory.getLogger(AbstractController.class);
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = { "filesDownload.htm" })
	public String crud() {
		return "tiles.view.body.userList";
	}
	
}

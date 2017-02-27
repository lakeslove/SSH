package com.lx.controllers.files;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lx.controllers.AbstractController;
import com.lx.utils.FilesIOUtil;

@Controller
public class FileDownloadController extends AbstractController{
	private static final Logger log = LoggerFactory.getLogger(AbstractController.class);
	
	@Value("${path.files.save}")
	private String pathFilesSave;
	
	@RequestMapping(value = { "filesDownload.htm" })
	public void filesDownload(HttpServletResponse response,Model model,String fileName) {
		FilesIOUtil.downloadFilesByJavaIo(response, pathFilesSave+fileName);
	}
	
}

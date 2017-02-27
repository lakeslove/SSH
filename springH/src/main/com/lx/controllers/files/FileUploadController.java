package com.lx.controllers.files;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.lx.controllers.AbstractController;
import com.lx.utils.FilesIOUtil;

@Controller
public class FileUploadController extends AbstractController{
	private static final Logger log = LoggerFactory.getLogger(AbstractController.class);
	
	@Value("${path.files.save}")
	private String pathFilesSave;
	
	@RequestMapping(value = { "filesUpload.htm" })
	public String filesUpload(MultipartHttpServletRequest multipartRequest) throws IllegalStateException, IOException {
//		FilesIOUtil.uploadMultiFiles(multipartRequest.getFileMap(),pathFilesSave);
		FilesIOUtil.uploadMultiFiles(multipartRequest.getFiles("files"),pathFilesSave);
		return "redirect:filesList.htm";
	}
	
	
}

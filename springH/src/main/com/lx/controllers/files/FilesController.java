package com.lx.controllers.files;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lx.controllers.AbstractController;
import com.lx.utils.FilesIOUtil;

@Controller
public class FilesController extends AbstractController{
	private static final Logger log = LoggerFactory.getLogger(AbstractController.class);
	
	//使用注解获取system.properies里面的值
	@Value("${path.files.save}")
	private String pathFilesSave;
	
	@RequestMapping(value = { "filesList.htm" })
	public String filesList(Model model) {
		String[] fileNameList = FilesIOUtil.getFileNameList(pathFilesSave);
		model.addAttribute("fileNameList", fileNameList);
		return "tiles.view.body.filesList";
	}
	
	@RequestMapping(value = { "filesDelete.htm" },method = RequestMethod.POST)
	public String filesdelete(Model model,String[] fileNames) {
		FilesIOUtil.deleteFilesByName(pathFilesSave, fileNames);
		return "redirect:filesList.htm";
	}
	
	@RequestMapping(value = { "fileDelete.htm" },method = RequestMethod.GET)
	public String filesdelete(Model model,String fileName) {
		FilesIOUtil.deleteFileByName(pathFilesSave+fileName);
		return "redirect:filesList.htm";
	}
}

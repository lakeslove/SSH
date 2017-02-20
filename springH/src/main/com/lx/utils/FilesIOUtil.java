package com.lx.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public class FilesIOUtil {
	private static Logger log = LoggerFactory.getLogger(FilesIOUtil.class);
	
	//用javaIo方法 文件下载
	public static void downloadFilesByJavaIo(HttpServletResponse response,String filePath){
		try(InputStream inStream = new FileInputStream(filePath);
			BufferedOutputStream outputStream = new BufferedOutputStream(response.getOutputStream())){
			String fileName = filePath.substring(filePath.lastIndexOf(File.separator)+1);
			response.setContentType("text/html;charset=utf-8");  
			response.setContentType("application/x-msdownload;");  
			response.addHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
			byte[] b = new byte[4096];
			int len;
			while ((len = inStream.read(b)) > 0){
				outputStream.write(b, 0, len);
			}	
			outputStream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	} 
	//用spring框架的方法 文件下载
	public static ResponseEntity<byte[]> downloadFilesBySpring(HttpServletResponse response,String filePath) throws IOException{
		String fileName = filePath.substring(filePath.lastIndexOf(File.separator)+1);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		headers.setContentDispositionFormData("attachment", fileName);
		return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(new File(filePath)), headers, HttpStatus.CREATED);
	}
	//单文件上传
	public static void uploadSingleFile(MultipartFile file,String savePath) throws IllegalStateException, IOException{
		String realFileName = file.getOriginalFilename();  
        // 创建文件   
        File dirPath = new File(savePath);   
        if (!dirPath.exists()) {
            dirPath.mkdir();   
        }   
        File uploadFile = new File(savePath + File.separator + realFileName);   
        file.transferTo(uploadFile);
	}
	//多文件上传
	public static void uploadMultiFiles(Map<String, MultipartFile> fileMap,String savePath) throws IllegalStateException, IOException{
		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
            MultipartFile mf = entity.getValue();   
            String fileName = mf.getOriginalFilename();   
            File uploadFile = new File(savePath + File.separator + fileName);
            mf.transferTo(uploadFile);
            // FileCopyUtils.copy(mf.getBytes(), uploadFile);   
        } 
	}
	//多文件上传
	public static void uploadMultiFiles(List<MultipartFile> fileList,String savePath) throws IllegalStateException, IOException{
		for (MultipartFile file : fileList) {  
            String fileName = file.getOriginalFilename();   
            File uploadFile = new File(savePath + File.separator + fileName);
            file.transferTo(uploadFile);
            // FileCopyUtils.copy(mf.getBytes(), uploadFile);   
        } 
	}
}

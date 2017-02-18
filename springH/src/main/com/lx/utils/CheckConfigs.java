package com.lx.utils;

import java.io.File;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.lx.daos.UserDao;

public class CheckConfigs implements ApplicationContextAware {
	private static final Logger log = LogManager.getLogger(CheckConfigs.class);
	private static final Logger sysLog = LogManager.getLogger("systemlog");
	private static String dbUrl;
	
	public static void setDbUrl(String dbUrl) {
		CheckConfigs.dbUrl = dbUrl;
	}

	private UserDao userDao;
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		try{
			checkEnvironmentVariable();
			checkDataSoucesContects();
		}catch(Exception e){
			log.fatal(e.getMessage(),e);
		}
	}
	
	private void checkEnvironmentVariable(){
		Map<String,String> environmentVariables = System.getenv();
	}
	
	private boolean validateFilePath(String path){
		File tmpFile = new File(path);
		if(tmpFile.exists()){
			return true;
		}else{
			return false;
		}
	}
	 
	private void checkDataSoucesContects(){
		try(Session session = userDao.openSession())
		{
			Transaction t = session.beginTransaction();
			t.commit();
		}catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}
}

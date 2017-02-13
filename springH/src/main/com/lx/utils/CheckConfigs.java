package com.lx.utils;

import java.io.File;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
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
	private static final String TSS_KZ_AUDIT = "TSS_KZ_AUDIT";
	private static final String[] wellnessEnvironmentVariables = new String[]{"TSS_KZ_LOGS",TSS_KZ_AUDIT};
	private static String jndiName;
	
	public static void setJndiName(String jndiName) {
		CheckConfigs.jndiName = jndiName;
	}

	private UserDao userDao;
	public void setSearchServiceUserDao(UserDao userDao) {
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
		for(String varibale:wellnessEnvironmentVariables){
			if(!environmentVariables.containsKey(varibale)){
				String errorMessage = PropertyUtil.getPropertyValue("errors.environmentVaribale.noExist");
				String logMessage = errorMessage.replace("{1}", varibale);
				sysLog.fatal(logMessage);
				log.fatal(logMessage);
				continue;
			}
			if(StringUtils.isEmpty(environmentVariables.get(varibale))){
				String errorMessage = PropertyUtil.getPropertyValue("errors.environmentVaribale.value.noExist");
				String logMessage = errorMessage.replace("{1}", varibale);
				sysLog.fatal(logMessage);
				log.fatal(logMessage);
				continue;
			}
			if(!validateFilePath(environmentVariables.get(varibale))){
				String errorMessage = PropertyUtil.getPropertyValue("errors.environmentVaribale.filePath.noExist");
				String logMessage = errorMessage.replace("{1}", varibale).replace("{2}", environmentVariables.get(varibale));
				sysLog.fatal(logMessage);
				log.fatal(logMessage);
			}
		}
		if(environmentVariables.containsKey(TSS_KZ_AUDIT)){
			String flagIsAdmin = PropertyUtil.getPropertyValue("flag.isAdmin");
			if("all".equals(flagIsAdmin) || "true".equals(flagIsAdmin)) {
			    String csvSubPath = PropertyUtil.getPropertyValue("medicalCheckup.download.filepath");
			    String auditPath = csvSubPath.replace("${TSS_KZ_AUDIT}", environmentVariables.get(TSS_KZ_AUDIT));
			    if(!validateFilePath(auditPath)){
			        String errorMessage = PropertyUtil.getPropertyValue("errors.filePath.noExist");
			        String logMessage = errorMessage.replace("{1}", auditPath);
			        sysLog.fatal(logMessage);
			        log.fatal(logMessage);
			     }
			 }
		}
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
		try(Session session = userDao.getSession())
		{
			Transaction t = session.beginTransaction();
			t.commit();
		}catch (Exception e) {
			String errorMessage = PropertyUtil.getPropertyValue("errors.jndi.notConnect");
			String logMessage = errorMessage.replace("{1}", jndiName);
			sysLog.fatal(logMessage);
			log.fatal(logMessage);
			log.error(e.getMessage(), e);
		}
	}
}

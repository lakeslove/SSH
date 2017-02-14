package com.lx.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppendLogsByAOP {
	private static Logger log = LoggerFactory.getLogger(AppendLogsByAOP.class);

	public void logOutBeforeMethod() {
		log.info("方法开始执行");
	}

	public void logOutAfterMethod() {
		log.info("方法执行完毕");
	}

	public void logOutAfterThrows() {
		log.error("方法出现异常");
	}

}

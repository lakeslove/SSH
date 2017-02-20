package com.lx.test;

import com.lx.utils.SpringEmailUtil;

public class TestEmail {
	public static void main(String[] args){
		SpringEmailUtil.Email email = new SpringEmailUtil().getEmail();		
		email.setFromPersonName("sbsbsb");
		email.setToEmailAddresses(new String[]{"1174756981@qq.com"});
		email.setSubject("测试");
		email.setContent("ceshidddda");
		try {
			SpringEmailUtil.sendEmail(email);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

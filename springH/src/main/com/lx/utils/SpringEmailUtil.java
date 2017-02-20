package com.lx.utils;

import java.io.File;
import java.util.concurrent.LinkedBlockingQueue;

import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

/**
 * CSV入出力用ユーティリティです。このクラスをインスタンス化することはできません。
 * 
 * @author YUAN
 */
public final class SpringEmailUtil {

	public static LinkedBlockingQueue<JavaMailSender> mailSenderQueue;
	
	private static void initMailSenderQueue(int numberOfMailSender){
		mailSenderQueue = new LinkedBlockingQueue<JavaMailSender>(numberOfMailSender);
		for (int i = 0; i < numberOfMailSender; i++) {
			JavaMailSender mailSender = getJavaMailSender();
			mailSenderQueue.add(mailSender);
		}
	}
	
	public static JavaMailSender getJavaMailSender() {
//		JavaMailSender mailSender = SpringBeanUtil.getBean(JavaMailSender.class, "mailSender");
		@SuppressWarnings("resource")
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("spring-mail.xml");     
		JavaMailSender mailSender = (JavaMailSender)context.getBean("mailSender"); 
		return mailSender;
	}

	public static void sendEmail(Email email) throws Exception {
		if(mailSenderQueue==null||mailSenderQueue.size()==0){
			initMailSenderQueue(1);
		}
		JavaMailSender mailSender = mailSenderQueue.take();
		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true, email.encoding);
			helper.setFrom(email.getFromEmailAddress(), email.getFromPersonName());
			helper.setTo(email.toEmailAddresses);
			helper.setSubject(email.getSubject());
			helper.setText(email.getContent());
			if (email.getAttachment() != null) {
				if (email.getAttachment().exists()) {
					helper.addAttachment(MimeUtility.encodeWord(email.getAttachment().getName(), "UTF-8", null),
							email.getAttachment());
				}
			}
			mailSender.send(message);
		} finally {
			mailSenderQueue.put(mailSender);
		}
	}
	public Email getEmail(){
		return new Email();
	}

	public class Email {
		private String encoding = "UTF-8";
		private String fromEmailAddress;
		private String fromPersonName;
		private String[] toEmailAddresses;
		private String subject;
		private String content;
		private File attachment;

		public String getEncoding() {
			return encoding;
		}

		public void setEncoding(String encoding) {
			this.encoding = encoding;
		}

		public String getFromEmailAddress() {
			return fromEmailAddress;
		}

		public void setFromEmailAddress(String fromEmailAddress) {
			this.fromEmailAddress = fromEmailAddress;
		}

		public String getFromPersonName() {
			return fromPersonName;
		}

		public void setFromPersonName(String fromPersonName) {
			this.fromPersonName = fromPersonName;
		}

		public String[] getToEmailAddresses() {
			return toEmailAddresses;
		}

		public void setToEmailAddresses(String[] toEmailAddresses) {
			this.toEmailAddresses = toEmailAddresses;
		}

		public String getSubject() {
			return subject;
		}

		public void setSubject(String subject) {
			this.subject = subject;
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}

		public File getAttachment() {
			return attachment;
		}

		public void setAttachment(File attachment) {
			this.attachment = attachment;
		}
	}
}

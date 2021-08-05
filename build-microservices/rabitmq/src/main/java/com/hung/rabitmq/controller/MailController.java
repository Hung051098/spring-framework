package com.hung.rabitmq.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class MailController {

	public final static Logger logger = LoggerFactory.getLogger(MailController.class);
	
	@Autowired
	JavaMailSender emailSender;
	
	public boolean SendMail(String to, String content, String topic) {
		try {
			SimpleMailMessage message = new SimpleMailMessage(); 
	        message.setFrom("levanhung051098@gmail.com");
	        message.setTo(to); 
	        message.setSubject(topic); 
	        message.setText(content);
	        emailSender.send(message);
	        return true;
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}
	
}

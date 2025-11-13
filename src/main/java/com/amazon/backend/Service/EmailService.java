package com.amazon.backend.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
@Service
public class EmailService {

	@Autowired
	public JavaMailSender javaMailSender;
	
	public void sendPlainEmail(String Subject,String desc,String fromAddress,String toAddressString) {
		
		SimpleMailMessage message=new SimpleMailMessage();
	
		message.setFrom(fromAddress);
		message.setTo(toAddressString);
		message.setSubject(Subject);
		message.setText(desc);
		
		javaMailSender.send(message);
	}
}

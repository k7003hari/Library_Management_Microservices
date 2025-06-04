package com.cts.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Repository;

@Repository
public class NotificationRepository {
	@Autowired
    private JavaMailSender mailSender;
 
    public String sendEmail(String recipientEmail, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("k7003hari@gmail.com"); // Configured in properties
        message.setTo(recipientEmail);
        message.setSubject(subject);
        message.setText(content);
 
        mailSender.send(message);
        return "Email sent successfully";
    }
 
	
}
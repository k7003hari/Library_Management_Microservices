package com.cts.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages="com.cts")
public class MailNotificationApplication {
 
	public static void main(String[] args) {
		SpringApplication.run(MailNotificationApplication.class, args);
	}
 
}

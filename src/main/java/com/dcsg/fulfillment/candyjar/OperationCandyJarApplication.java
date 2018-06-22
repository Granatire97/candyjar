package com.dcsg.fulfillment.candyjar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OperationCandyJarApplication<Gate> {

	public static void main(String[] args) {
		SpringApplication.run(OperationCandyJarApplication.class, args);
		//SpringApplication.run(FtpConfiguration.class);
	}
	
}

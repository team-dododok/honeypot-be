package com.dodok.honeypot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class HoneypotApplication {

	public static void main(String[] args) {
		SpringApplication.run(HoneypotApplication.class, args);
	}

}

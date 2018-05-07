package com.newscenter.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class NewsCenterApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewsCenterApplication.class, args);
	}
}

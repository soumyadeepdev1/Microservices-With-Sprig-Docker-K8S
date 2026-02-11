package com.university.administration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableConfigurationProperties
@EnableFeignClients(value = "com.university.administration.feign")
public class AdministrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdministrationApplication.class, args);
	}

}

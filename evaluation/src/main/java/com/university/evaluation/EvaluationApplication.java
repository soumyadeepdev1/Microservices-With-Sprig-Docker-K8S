package com.university.evaluation;

import com.university.evaluation.dto.AppInfoDto;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class EvaluationApplication {

	public static void main(String[] args) {
		SpringApplication.run(EvaluationApplication.class, args);
	}

}

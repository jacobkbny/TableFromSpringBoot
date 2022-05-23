package com.tistory.jacobcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
@SpringBootApplication
public class TableApplication {

	public static void main(String[] args) {
		SpringApplication.run(TableApplication.class, args);
	}

}

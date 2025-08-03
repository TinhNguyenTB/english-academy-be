package com.englishacademy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
@SpringBootApplication
public class EnglearnApplication {

	public static void main(String[] args) {
		SpringApplication.run(EnglearnApplication.class, args);
	}

}

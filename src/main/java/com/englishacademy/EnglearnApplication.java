package com.englishacademy;

import com.englishacademy.config.dotenv.DotenvConfig;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
@EnableCaching
@EnableAspectJAutoProxy
@SpringBootApplication
public class EnglearnApplication {

	public static void main(String[] args) {
		SpringApplication.run(EnglearnApplication.class, args);
	}

}

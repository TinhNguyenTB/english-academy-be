package com.englishacademy.config.dotenv;

import io.github.cdimascio.dotenv.Dotenv;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;

import java.util.Map;

public class DotenvConfig implements EnvironmentPostProcessor {


    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();

        MutablePropertySources propertySources = environment.getPropertySources();
        MapPropertySource dotenvSource = new MapPropertySource("dotenvProperties", dotenv.entries().stream()
                .collect(java.util.stream.Collectors.toMap(
                        entry -> entry.getKey(),
                        entry -> entry.getValue()
                )));

        propertySources.addFirst(dotenvSource);
    }
}

package com.englishacademy.config.app;

import com.englishacademy.entity.Role;
import com.englishacademy.entity.User;
import com.englishacademy.repository.RoleRepository;
import com.englishacademy.repository.UserRepository;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@Log4j2
public class AppConfig implements WebMvcConfigurer {

    private static final String API_PREFIX = "/api/v1";
    private final PasswordEncoder passwordEncoder;

    public AppConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void configurePathMatch(PathMatchConfigurer pathMatchConfigurer) {
        pathMatchConfigurer.addPathPrefix(API_PREFIX, clazz -> true); // áp dụng cho tất cả controller
    }

    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository, RoleRepository roleRepository) {
        return args -> {
            if (userRepository.findByName("admin").isEmpty()) {
                Role adminRole = roleRepository.findByName("ADMIN")
                        .orElseGet(() -> {
                            Role newRole = new Role();
                            newRole.setName("ADMIN");
                            return roleRepository.save(newRole);
                        });
                User user = User.builder()
                        .name("admin")
                        .password(passwordEncoder.encode("123"))
                        .email("admin@gmail.com")
                        .role(adminRole)
                        .build();
                userRepository.save(user);
                log.warn("The admin account has been created with default password:123, please change it.");
            }
        };
    }
}

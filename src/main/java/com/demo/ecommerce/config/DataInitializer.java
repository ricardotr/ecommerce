package com.demo.ecommerce.config;

import java.util.List;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.demo.ecommerce.user.domain.Role;
import com.demo.ecommerce.user.domain.User;
import com.demo.ecommerce.user.infrastructure.repository.RoleRepository;
import com.demo.ecommerce.user.infrastructure.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class DataInitializer {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public DataInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder,RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Bean
    @Order(1)
    CommandLineRunner initRoles() {
        return args -> {
            if (roleRepository.findAll().isEmpty()) {
                log.info("Initializing roles...");
                Role userRole = Role.builder().name("APP_USER").build();
                roleRepository.saveAll(List.of(userRole));
                log.info("Initializing roles ends.");
            }
        };
    }

    @Bean
    @Order(2)
    CommandLineRunner initDefaultUser() {
        return args -> {
            if (userRepository.findByUsername("demouser").isEmpty()) {
                log.info("Initializing default user...");
                Role appUserRole = roleRepository.findByName("APP_USER")
                    .orElseThrow(() -> new IllegalArgumentException("Role not found"));
                User appUser = new User();
                appUser.setUsername("demouser");
                appUser.setEmail("demouser@example.com");
                appUser.setPassword(passwordEncoder.encode("demouser1"));
                appUser.setRoles(Set.of(appUserRole));
                userRepository.save(appUser);
                log.info("Initializing default user ends.");
            }
        };
    }
}

package com.example.smarticity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.reactive.ReactiveSecurityAutoConfiguration;

@SpringBootApplication
public class SmartiCityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmartiCityApplication.class, args);
    }

}

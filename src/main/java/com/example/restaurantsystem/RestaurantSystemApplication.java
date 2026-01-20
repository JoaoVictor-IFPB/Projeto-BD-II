package com.example.restaurantsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.restaurantsystem.repository.jpa")
@EnableMongoRepositories(basePackages = "com.example.restaurantsystem.repository.mongo")
@EnableCaching

public class RestaurantSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestaurantSystemApplication.class, args);
    }

}
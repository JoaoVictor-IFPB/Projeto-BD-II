package com.example.restaurantsystem.config;

import io.minio.MinioClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MinioConfig {

    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder()
                .endpoint("http://localhost:9000") // URL que você configurou no Docker
                .credentials("minioadmin", "minioadminpassword")
                .build();
    }
}

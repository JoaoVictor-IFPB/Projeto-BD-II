package com.example.restaurantsystem.service;

import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
public class FileService {
    @Autowired
    private MinioClient minioClient;

    public String uploadImage(MultipartFile file) throws Exception {
        String fileName = UUID.randomUUID() + "-" + file.getOriginalFilename();

        minioClient.putObject(
                PutObjectArgs.builder()
                        .bucket("restaurante") // Nome do bucket (deve ser criado no MinIO)
                        .object(fileName)
                        .stream(file.getInputStream(), file.getSize(), -1)
                        .contentType(file.getContentType())
                        .build()
        );

        return "http://localhost:9000/restaurante/" + fileName;
    }
}
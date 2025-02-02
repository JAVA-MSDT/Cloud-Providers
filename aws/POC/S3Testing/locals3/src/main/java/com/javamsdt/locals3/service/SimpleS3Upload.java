package com.javamsdt.locals3.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.nio.file.Path;

@Component
@RequiredArgsConstructor
public class SimpleS3Upload {
    @Value("${aws.s3.bucket.name}")
    private String bucketName;

    private final S3Client s3Client;

    public boolean uploadFileToS3(String fileName, String bucketName) throws IOException {
        Path path = Path.of(fileName);
        PutObjectRequest objectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(fileName)
                .build();
        s3Client.putObject(objectRequest,
                RequestBody.fromFile(path));
        return true;
    }

    public String getClassName() {
        return this.getClass().getName();
    }

}

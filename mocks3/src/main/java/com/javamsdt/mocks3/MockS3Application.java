package com.javamsdt.mocks3;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import software.amazon.awssdk.services.s3.S3Client;

@SpringBootApplication
@RequiredArgsConstructor
public class MockS3Application implements ApplicationRunner {
    private final S3Client s3Client;

    public static void main(String[] args) {
        SpringApplication.run(MockS3Application.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("Testing S3Client Access:: ");
        s3Client.listBuckets().buckets().forEach(System.out::println);
    }
}

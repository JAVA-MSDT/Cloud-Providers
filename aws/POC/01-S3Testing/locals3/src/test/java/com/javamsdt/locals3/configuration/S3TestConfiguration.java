package com.javamsdt.locals3.configuration;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

import java.net.URI;

@TestConfiguration
public class S3TestConfiguration {
    private final static String REGION_NAME = "eu-central-1";
    private final static String END_POINT_URL = "http://localhost:";
    private final static int PORT = 8800;
    private final static String ACCESS_KEY = "accessKey";
    private final static String SECRET_KEY = "secretKey";

    @Bean
    public S3Client s3TestClient() {
        return S3Client.builder()
                .endpointOverride(URI.create(END_POINT_URL + PORT))
                .region(Region.of(REGION_NAME))
                .credentialsProvider(() -> AwsBasicCredentials.create(ACCESS_KEY, SECRET_KEY))
                .build();
    }

//    @Bean(initMethod = "start", destroyMethod = "shutdown")
//    public LocalS3 localS3() {
//        return LocalS3.builder()
//                .port(PORT)
//                .build();
//    }
}

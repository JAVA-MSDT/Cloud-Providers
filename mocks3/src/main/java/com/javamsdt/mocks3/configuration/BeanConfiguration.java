package com.javamsdt.mocks3.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class BeanConfiguration {

    @Value("${aws.region.name}")
    private String regionName;
    @Value("${aws.s3.accessKey}")
    private String accessKey;
    @Value("${aws.s3.secretKey}")
    private String secretKey;

    @Bean
    public S3Client s3Client(AwsBasicCredentials credentials, Region region) {
        System.out.println("regionName:: " + regionName);
        System.out.println("accessKey:: " + accessKey);
        System.out.println("secretKey:: " + secretKey);
        return S3Client
                .builder()
                .region(region)
                .credentialsProvider(() -> credentials)
                .build();
    }

    @Bean
    public Region region() {
        return Region.of(regionName);
    }

    @Bean
    public AwsBasicCredentials credentials() {
        return AwsBasicCredentials.create(accessKey, secretKey);
    }
}

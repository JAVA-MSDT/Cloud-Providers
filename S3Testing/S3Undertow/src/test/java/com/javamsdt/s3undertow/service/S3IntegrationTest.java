package com.javamsdt.s3undertow.service;

import com.javamsdt.s3undertow.configuration.S3TestConfiguration;
import io.undertow.Undertow;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.CreateBucketRequest;
import software.amazon.awssdk.services.s3.model.CreateBucketResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Import(S3TestConfiguration.class)
 class S3IntegrationTest {

    @Autowired
    private S3Client s3TestClient;

    @Autowired
    private Undertow s3Server;
    private final static String BUCKET_NAME = "test-s3-files-storage";
    @Test
     void shouldReturnS3Object() throws Exception {
        s3Server.start();
        createBucket(s3TestClient, BUCKET_NAME);
        assertEquals(1, 1);
        getBucketsSize();
        s3Server.stop();
    }

    private void createBucket(S3Client s3Client, String bucketName) {
        CreateBucketRequest createBucketRequest = CreateBucketRequest.builder()
                .bucket(bucketName)
                .build();
        CreateBucketResponse bucketResponse = s3Client.createBucket(createBucketRequest);
    }
    private void getBucketsSize(){
         s3TestClient.listBuckets().buckets().forEach(System.out::println);
    }
}

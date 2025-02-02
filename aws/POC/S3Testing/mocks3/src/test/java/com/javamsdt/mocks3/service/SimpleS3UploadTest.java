package com.javamsdt.mocks3.service;

import com.javamsdt.mocks3.configuration.S3TestConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.CreateBucketRequest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Import(S3TestConfiguration.class)
class SimpleS3UploadTest {
    @Autowired
    private S3Client s3TestClient;
    private final static String BUCKET_NAME = "test-s3-files-storage";


    @Test
    void uploadFileToS3() {
        createBucket(s3TestClient, BUCKET_NAME);
        System.out.println("Bucket Size" + s3TestClient.listBuckets().buckets().size());
        assertNotNull(s3TestClient);
    }

    private void createBucket(S3Client s3Client, String bucketName) {
        CreateBucketRequest createBucketRequest = CreateBucketRequest.builder()
                .bucket(bucketName)
                .build();
        s3Client.createBucket(createBucketRequest);
    }
}
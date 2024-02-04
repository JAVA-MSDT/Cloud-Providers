package com.javamsdt.s3undertow.service;

import com.javamsdt.s3undertow.configuration.S3TestConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import software.amazon.awssdk.core.waiters.WaiterResponse;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.CreateBucketRequest;
import software.amazon.awssdk.services.s3.model.HeadBucketRequest;
import software.amazon.awssdk.services.s3.model.HeadBucketResponse;
import software.amazon.awssdk.services.s3.model.S3Exception;
import software.amazon.awssdk.services.s3.waiters.S3Waiter;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Import(S3TestConfiguration.class)
class SimpleS3UploadTest {
    @Autowired
    private S3Client s3TestClient;
    @Autowired
    private SimpleS3Upload simpleS3Upload;
    private final static String BUCKET_NAME = "test-s3-files-storage";


    @Test
    void uploadFileToS3() {
        createBucket(s3TestClient, BUCKET_NAME);
        System.out.println(simpleS3Upload.getClassName());
        System.out.println("Bucket Size" + s3TestClient.listBuckets().buckets().size());

        assertNotNull(simpleS3Upload);
        assertNotNull(s3TestClient);
    }

    private void createBucket(S3Client s3Client, String bucketName) {
        CreateBucketRequest createBucketRequest = CreateBucketRequest.builder()
                .bucket(bucketName)
                .build();
        s3Client.createBucket(createBucketRequest);
    }

    private void generateBucket(S3Client s3Client, String bucketName) {
        try {
            S3Waiter s3Waiter = s3Client.waiter();
            CreateBucketRequest bucketRequest = CreateBucketRequest.builder()
                    .bucket(bucketName)
                    .build();

            s3Client.createBucket(bucketRequest);
            HeadBucketRequest bucketRequestWait = HeadBucketRequest.builder()
                    .bucket(bucketName)
                    .build();

            // Wait until the bucket is created and print out the response.
            WaiterResponse<HeadBucketResponse> waiterResponse = s3Waiter.waitUntilBucketExists(bucketRequestWait);
            waiterResponse.matched().response().ifPresent(System.out::println);
            System.out.println(bucketName + " is ready");

        } catch (S3Exception e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        }
    }
}
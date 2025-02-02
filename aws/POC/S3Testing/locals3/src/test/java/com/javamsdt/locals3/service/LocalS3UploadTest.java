package com.javamsdt.locals3.service;

import com.robothy.s3.rest.LocalS3;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.io.IOException;
import java.net.URI;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LocalS3UploadTest {
    private static final String BUCKET_NAME = "test-bucket";
    private static final String OBJECT_KEY = "test-object";
    private static final String CONTENT = "Hello, S3!";

    private static LocalS3 s3Mock;
    private static String endpoint;

    @BeforeAll
    public static void setUp() throws IOException {

        s3Mock = LocalS3.builder().dataPath("temporary").port(9900).build();
        s3Mock.start();
        System.out.println("DataPath 2:: " + s3Mock.getDataPath().toAbsolutePath());
        endpoint = "http://localhost:" + s3Mock.getPort();
        System.out.println("endpoint:: " + endpoint);
    }

    @AfterAll
    public static void tearDown() {
        s3Mock.shutdown();
    }

    @Test
    public void testS3BucketInteraction() {
        // Create an S3 client using the local S3 server endpoint
        S3Client s3Client = S3Client.builder()
                .region(Region.US_EAST_1)
                .endpointOverride(URI.create(endpoint))
                .credentialsProvider(() -> AwsBasicCredentials.create("accessKey", "secretKey"))
                .build();

        // Create a bucket
        s3Client.createBucket(CreateBucketRequest.builder().bucket(BUCKET_NAME).build());

        // Upload an object to the bucket
        s3Client.putObject(PutObjectRequest.builder()
                .bucket(BUCKET_NAME)
                .key(OBJECT_KEY)
                .build(), RequestBody.fromString(CONTENT));

        // Retrieve the object
        ResponseInputStream<GetObjectResponse> getObjectResponse = s3Client.getObject(GetObjectRequest.builder()
                .bucket(BUCKET_NAME)
                .key(OBJECT_KEY)
                .build());

        // Verify the content of the retrieved object
        String retrievedContent = getObjectResponse.response().contentDisposition();
        assertEquals(CONTENT, retrievedContent);

        // List objects in the bucket
        ListObjectsV2Response listObjectsResponse = s3Client.listObjectsV2(ListObjectsV2Request.builder()
                .bucket(BUCKET_NAME)
                .build());

        // Verify that the object is in the bucket
        assertTrue(listObjectsResponse.contents().stream().anyMatch(obj -> obj.key().equals(OBJECT_KEY)));
    }
}
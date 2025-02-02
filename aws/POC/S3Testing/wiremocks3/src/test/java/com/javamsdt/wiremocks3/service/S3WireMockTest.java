package com.javamsdt.wiremocks3.service;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class S3WireMockTest {

    private final WireMockServer wireMockServer = new WireMockServer();
    private S3Client s3Client;

    @BeforeEach
    public void setup() {
        wireMockServer.start();
        WireMock.configureFor("localhost", wireMockServer.port());
         s3Client = S3Client.builder()
                .region(Region.US_EAST_1)
                .endpointOverride(URI.create(wireMockServer.baseUrl()))
                .credentialsProvider(() -> AwsBasicCredentials.create("accessKey", "secretKey"))
                .build();
    }

    @AfterEach
    public void tearDown() {
        wireMockServer.stop();
    }

    @Test
     void testServer() {
        System.out.println("baseUrl:: " + wireMockServer.baseUrl());
        System.out.println("s3Client baseUrl:: " + s3Client.serviceClientConfiguration().endpointOverride().get());
        assertNotNull(wireMockServer);
        assertNotNull(s3Client);
    }
   @Test
    public void testS3Operations() throws IOException {
        // Set up WireMock stubs for S3 operations
        stubFor(put(urlPathMatching("/test-bucket"))
                .willReturn(aResponse()
                        .withStatus(200)));

        stubFor(put(urlPathMatching("/test-bucket/test-object"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody("Object successfully uploaded")));

        stubFor(get(urlPathMatching("/test-bucket/test-object"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody("Hello, WireMock!")));



       s3Client.createBucket(CreateBucketRequest.builder()
               .bucket("test-bucket")
               .build());
        // Upload object
        s3Client.putObject(PutObjectRequest.builder()
                .bucket("test-bucket")
                .key("test-object")
                .build(), RequestBody.fromString("Hello, S3 in WireMock!"));

        // Download object
        ResponseInputStream<GetObjectResponse> getObjectResponse = s3Client.getObject(GetObjectRequest.builder()
                .bucket("test-bucket")
                .key("test-object")
                .build());

        // Perform assertions based on the downloaded content
        String downloadedContent = new String(getObjectResponse.readAllBytes(), StandardCharsets.UTF_8);
        System.out.println("Downloaded content: " + downloadedContent);

        // Clean up (delete object)
        s3Client.deleteObject(DeleteObjectRequest.builder()
                .bucket("test-bucket")
                .key("test-object")
                .build());
    }
}


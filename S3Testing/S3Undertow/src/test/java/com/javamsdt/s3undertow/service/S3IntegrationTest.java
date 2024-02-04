package com.javamsdt.s3undertow.service;

import com.javamsdt.s3undertow.configuration.S3TestConfiguration;
import io.undertow.Undertow;
import io.undertow.server.BlockingHttpExchange;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.CreateBucketRequest;

import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.Path;

@SpringBootTest
@Import(S3TestConfiguration.class)
 class S3IntegrationTest {

    @Autowired
    private S3Client s3TestClient;
    private final static String BUCKET_NAME = "test-s3-files-storage";
    @Test
     void shouldReturnS3Object(@TempDir Path tempDir) throws Exception {
        final int S3_MOCK_SERVER_PORT = 9999;

        Undertow s3Server = Undertow.builder()
                .addHttpListener(S3_MOCK_SERVER_PORT, "0.0.0.0")
                .setHandler(new S3HttpHandler())
                .build();

        s3Server.start();

        createBucket(s3TestClient, BUCKET_NAME);
//        String serviceEndpoint = String.format("http://localhost:%d", S3_MOCK_SERVER_PORT);

//        try (S3Client s3Client = S3Client.builder()
//                .endpointOverride(URI.create(serviceEndpoint))
//                .httpClient(UrlConnectionHttpClient.builder().build())
//                .region(Region.of("eu-central-1"))
//                .credentialsProvider()
//                .build()) {
//
//            GetObjectRequest request = GetObjectRequest.builder()
//                    .bucket("my-bucket")
//                    .key("my-object")
//                    .build();
//
//            Path targetPath = tempDir.resolve("result");
//
//            s3TestClient.getObject(request, targetPath);
//
//            String objectContent = Files.readString(targetPath);
//            System.out.printf("Object content: %s%n", objectContent);
//            assertEquals("Hello world", objectContent);

        s3Server.stop();
    }

    static class S3HttpHandler implements HttpHandler {

        @Override
        public void handleRequest(HttpServerExchange exchange) throws Exception {
            if (exchange.isInIoThread()) {
                exchange.dispatch(this);
                return;
            }

            System.out.printf("Request method: %s%n", exchange.getRequestMethod());
            System.out.printf("Request path: %s%n", exchange.getRequestPath());

            try (BlockingHttpExchange blockingExchange = exchange.startBlocking();
                 OutputStream outputStream = exchange.getOutputStream();
                 OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
                 BufferedWriter writer = new BufferedWriter(outputStreamWriter)) {

                writer.write("Hello world");
            }
        }

    }

    private void createBucket(S3Client s3Client, String bucketName) {
        CreateBucketRequest createBucketRequest = CreateBucketRequest.builder()
                .bucket(bucketName)
                .build();
        s3Client.createBucket(createBucketRequest);
    }
}

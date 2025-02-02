package com.javamsdt.s3undertow.configuration;

import io.undertow.Undertow;
import io.undertow.server.BlockingHttpExchange;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.core.SdkSystemSetting;
import software.amazon.awssdk.http.urlconnection.UrlConnectionHttpClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
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
        System.setProperty(SdkSystemSetting.CBOR_ENABLED.defaultValue(), "false");
        return S3Client.builder()
                .endpointOverride(URI.create(END_POINT_URL + PORT))
                .region(Region.of(REGION_NAME))
                .httpClient(UrlConnectionHttpClient.builder().build())
                .credentialsProvider(() -> AwsBasicCredentials.create(ACCESS_KEY, SECRET_KEY))
                .build();
    }

    @Bean()
    public Undertow s3Server() {
        return Undertow.builder()
                .addHttpListener(PORT, "0.0.0.0")
                .setHandler(new S3HttpHandler())
                .build();
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
}

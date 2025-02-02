package com.javamsdt.locals3.service;

import com.robothy.s3.rest.LocalS3;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertTrue;

class LocalS3Test {

    @Test
    void start() throws Exception {
        LocalS3 localS3 = LocalS3.builder()
                .dataPath("temporary")
                .port(19090)
                .build();
        localS3.start();
        System.out.println("DataPath 2:: " + localS3.getDataPath().toAbsolutePath());
        localS3.shutdown();

        Path tempDirectory = Files.createTempDirectory("local-s3");
        String dataPath = tempDirectory.toAbsolutePath().toString();
        System.out.println("dataPath:: " + dataPath);
        localS3 = LocalS3.builder()
                .port(19090)
                .dataPath(dataPath)
                .build();
        localS3.start();
        localS3.shutdown();
        localS3.shutdown();
    }

    @Test
    void testBuilder() {
        LocalS3 localS3 = LocalS3.builder()
                .dataPath("/tmp")
                .build();
        assertTrue(localS3.getDataPath().endsWith("tmp"));
        assertTrue(localS3.getPort() > 0);
    }

}
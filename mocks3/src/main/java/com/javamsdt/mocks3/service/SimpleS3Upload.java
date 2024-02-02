package com.javamsdt.mocks3.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SimpleS3Upload {
    @Value("${aws.s3.bucket.name}")
    private String bucketName;

}

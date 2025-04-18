package com.javamsdt.ec2metadataui.rest;

import com.javamsdt.ec2metadataui.service.MetadataService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/metadata")
public class MetadataRestController {
    private final MetadataService metadataService;

    public MetadataRestController(MetadataService metadataService) {
        this.metadataService = metadataService;
    }

    @GetMapping("/ec2")
    public String getMetadata() {
        return String.format("""
            {
                "region": "%s",
                "availabilityZone": "%s"
            }
            """, metadataService.getRegion(), metadataService.getAvailabilityZone());
    }
}

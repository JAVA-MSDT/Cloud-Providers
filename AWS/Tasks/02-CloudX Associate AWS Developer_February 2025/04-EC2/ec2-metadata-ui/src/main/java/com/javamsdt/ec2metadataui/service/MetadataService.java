package com.javamsdt.ec2metadataui.service;

import com.amazonaws.SdkClientException;
import com.amazonaws.util.EC2MetadataUtils;
import org.springframework.stereotype.Service;

import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class MetadataService {
    public static final String RUNNING_LOCAL = "Running local";
    Logger logger = Logger.getLogger(MetadataService.class.getName());
    public String getAvailabilityZone() {
        try {
            String az = EC2MetadataUtils.getAvailabilityZone();
            return (az != null && !az.isEmpty()) ? az : RUNNING_LOCAL;
        } catch (Exception sce) {
            logger.log(Level.WARNING,
                    String.format("Application %s, Original message: %s.", RUNNING_LOCAL,  sce.getLocalizedMessage()));
            return RUNNING_LOCAL;
        }
    }

    public String getRegion() {
        String az = getAvailabilityZone();
        return az.equals(RUNNING_LOCAL) ? RUNNING_LOCAL: az.substring(0, az.length() - 1);
    }
}

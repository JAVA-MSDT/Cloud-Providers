package com.javamsdt.ec2metadataui.web;

import com.javamsdt.ec2metadataui.service.MetadataService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MetadataWebController {
    private final MetadataService metadataService;

    public MetadataWebController(MetadataService metadataService) {
        this.metadataService = metadataService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("region", metadataService.getRegion());
        model.addAttribute("availabilityZone", metadataService.getAvailabilityZone());
        return "index";
    }
}

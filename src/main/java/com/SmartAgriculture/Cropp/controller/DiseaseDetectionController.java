package com.SmartAgriculture.Cropp.controller;

import java.io.IOException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.SmartAgriculture.Cropp.Service.DiseaseDetectionService;
import com.SmartAgriculture.Cropp.dtos.DiseaseDetectionResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class DiseaseDetectionController {

    private final DiseaseDetectionService diseaseDetectionService;

    @PostMapping("/disease/detect")
    public ResponseEntity<DiseaseDetectionResponse> detectDisease(
            @RequestParam("image") MultipartFile image) throws IOException {
        
        // Basic validation
        if (image.isEmpty()) {
            throw new IllegalArgumentException("Image file is required");
        }
        
        DiseaseDetectionResponse response = diseaseDetectionService.detectdisease(image);
        return ResponseEntity.ok(response);
    }
}
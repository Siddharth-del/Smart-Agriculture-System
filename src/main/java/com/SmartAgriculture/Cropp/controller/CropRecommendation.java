package com.SmartAgriculture.Cropp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SmartAgriculture.Cropp.Service.CropRecommendationService;
import com.SmartAgriculture.Cropp.dtos.CropRecommendationResponse;
import com.SmartAgriculture.Cropp.dtos.CropRequestDTO;
import com.SmartAgriculture.Cropp.dtos.DiseaseDetectionResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/ml")
@RequiredArgsConstructor
public class CropRecommendation {

    private final CropRecommendationService cropRecommendationService;

    @PostMapping("/recommend-crop")
    public ResponseEntity<CropRecommendationResponse> recommendCrop(@Valid @RequestBody CropRequestDTO request) {
        CropRecommendationResponse response = cropRecommendationService.recommendCrop(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}

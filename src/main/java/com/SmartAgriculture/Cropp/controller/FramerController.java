package com.SmartAgriculture.Cropp.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SmartAgriculture.Cropp.Service.CropRecommendationService;
import com.SmartAgriculture.Cropp.dtos.CropRecommendationResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/framer")
@RequiredArgsConstructor
public class FramerController {
   
    private final CropRecommendationService cropRecommendationService;
    
    // @GetMapping("/history")
    // public ResponseEntity<List<CropRecommendationResponse>> getHistory(User user){
    //    CropRecommendationResponse history=cropRecommendationService.getHistory();
    //    return new ResponseEntity<>(history,HttpStatus.OK);
    // }
}

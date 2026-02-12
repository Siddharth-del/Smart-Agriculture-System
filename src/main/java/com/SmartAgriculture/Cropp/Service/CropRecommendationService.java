package com.SmartAgriculture.Cropp.Service;

import org.springframework.stereotype.Service;

import com.SmartAgriculture.Cropp.dtos.CropRequestDTO;
import com.SmartAgriculture.Cropp.dtos.RecommendationResponseDTO;


public interface CropRecommendationService {
    RecommendationResponseDTO recommendCrop(CropRequestDTO request);
}

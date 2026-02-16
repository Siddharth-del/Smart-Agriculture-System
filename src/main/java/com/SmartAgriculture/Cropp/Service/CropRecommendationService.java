package com.SmartAgriculture.Cropp.Service;

import org.springframework.stereotype.Service;

import com.SmartAgriculture.Cropp.dtos.CropRecommendationResponse;
import com.SmartAgriculture.Cropp.dtos.CropRequestDTO;
import com.SmartAgriculture.Cropp.dtos.DiseaseDetectionResponse;

public interface CropRecommendationService {
    CropRecommendationResponse recommendCrop(CropRequestDTO request);
}

package com.SmartAgriculture.Cropp.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.SmartAgriculture.Cropp.dtos.CropRecommendationResponse;
import com.SmartAgriculture.Cropp.dtos.CropRequestDTO;
import com.SmartAgriculture.Cropp.dtos.DiseaseDetectionResponse;

public interface CropRecommendationService {
    CropRecommendationResponse recommendCrop(CropRequestDTO request);
       List<CropRecommendationResponse> getCropByName(String name);
}

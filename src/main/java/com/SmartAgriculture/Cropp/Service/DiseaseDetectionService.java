package com.SmartAgriculture.Cropp.Service;

import com.SmartAgriculture.Cropp.dtos.DiseaseRequestDTO;
import com.SmartAgriculture.Cropp.dtos.RecommendationResponseDTO;

public interface DiseaseDetectionService {
    RecommendationResponseDTO detectdisease(DiseaseRequestDTO request);
}

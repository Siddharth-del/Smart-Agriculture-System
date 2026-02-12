package com.SmartAgriculture.Cropp.dtos;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RecommendationResponseDTO {

    private String recommendedCrop;
    private Double cropConfidence;

    private String predictedDisease;
    private Double diseaseConfidence;

    private String fertilizerRecommendation;
    private String pesticideRecommendation;

    private String explanation;

    private LocalDateTime generatedAt;
}

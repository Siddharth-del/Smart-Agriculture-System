package com.SmartAgriculture.Cropp.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MlPredictionResponse {
    private String cropName;
    private Double confidence;

}

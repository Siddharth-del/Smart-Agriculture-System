package com.SmartAgriculture.Cropp.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdvisoryResponse {

    private String fertilizerRecommendation;
    private String pesticideRecommendation;
    private String explanation;
}

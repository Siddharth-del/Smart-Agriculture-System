package com.SmartAgriculture.Cropp.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CropRequestDTO {

    private Double temperature;
    private Double humidity;
    private Double soilMoisture;

    private Double nitrogen;
    private Double phosphorus;
    private Double potassium;

    private String location;
}

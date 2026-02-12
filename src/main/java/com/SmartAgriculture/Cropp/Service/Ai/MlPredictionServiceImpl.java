package com.SmartAgriculture.Cropp.Service.Ai;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.SmartAgriculture.Cropp.dtos.MlPredictionResponse;
import com.SmartAgriculture.Cropp.model.SensorData;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MlPredictionServiceImpl implements MlPredictionService {

    private final RestTemplate restTemplate;

    @Value("${ml.api.url}")
    private String mlApiUrl;

    @Override
    public MlPredictionResponse predictCrop(SensorData data) {

        Map<String, Object> request = Map.of(
                "temperature", data.getTemperature(),
                "humidity", data.getHumidity(),
                "nitrogen", data.getNitrogen(),
                "phosphorus", data.getPhosphorus(),
                "potassium", data.getPotassium(),
                "soilMoisture", data.getSoilMoisture()
        );

        try {
            ResponseEntity<MlPredictionResponse> response =
                    restTemplate.postForEntity(
                            mlApiUrl,
                            request,
                            MlPredictionResponse.class
                    );

            if (!response.getStatusCode().is2xxSuccessful() || response.getBody() == null) {
                throw new RuntimeException("Invalid response from ML service");
            }

            return response.getBody();

        } catch (Exception e) {
            throw new RuntimeException("ML prediction failed", e);
        }
    }
}

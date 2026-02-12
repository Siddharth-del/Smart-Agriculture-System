package com.SmartAgriculture.Cropp.Service.Ai;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import com.SmartAgriculture.Cropp.dtos.AdvisoryResponse;
import com.SmartAgriculture.Cropp.model.SensorData;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AiAdvisoryServiceImpl implements AiAdvisoryService {

    private final ChatClient chatClient;
    private final ObjectMapper objectMapper;

    @Override
    public AdvisoryResponse generateDiseaseAdvisory(String diseaseName) {

        String prompt = """
        You are an agricultural expert.

        Provide:
        - fertilizerRecommendation
        - pesticideRecommendation
        - explanation

        Disease: %s

        Return strictly in JSON:
        {
          "fertilizerRecommendation": "",
          "pesticideRecommendation": "",
          "explanation": ""
        }
        """.formatted(diseaseName);

        String response = chatClient.prompt()
                .user(prompt)
                .call()
                .content();

        return parseJson(response);
    }

    @Override
    public AdvisoryResponse generateCropAdvisory(String cropName,
                                                 SensorData sensorData) {

        String prompt = """
        You are an agricultural expert.

        Crop: %s
        Temperature: %s
        Humidity: %s
        Nitrogen: %s
        Phosphorus: %s
        Potassium: %s
        Soil Moisture: %s

        Provide:
        - fertilizerRecommendation
        - pesticideRecommendation
        - explanation

        Return strictly in JSON:
        {
          "fertilizerRecommendation": "",
          "pesticideRecommendation": "",
          "explanation": ""
        }
        """.formatted(
                cropName,
                sensorData.getTemperature(),
                sensorData.getHumidity(),
                sensorData.getNitrogen(),
                sensorData.getPhosphorus(),
                sensorData.getPotassium(),
                sensorData.getSoilMoisture()
        );

        String response = chatClient.prompt()
                .user(prompt)
                .call()
                .content();

        return parseJson(response);
    }

    private AdvisoryResponse parseJson(String response) {
        try {
            return objectMapper.readValue(response, AdvisoryResponse.class);
        } catch (Exception e) {
            throw new RuntimeException("Invalid AI JSON response: " + response, e);
        }
    }
}

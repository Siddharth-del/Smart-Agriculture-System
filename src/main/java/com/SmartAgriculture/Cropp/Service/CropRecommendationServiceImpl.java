package com.SmartAgriculture.Cropp.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.SmartAgriculture.Cropp.Service.Ai.AiAdvisoryService;
import com.SmartAgriculture.Cropp.Service.Ai.MlPredictionService;
import com.SmartAgriculture.Cropp.dtos.AdvisoryResponse;
import com.SmartAgriculture.Cropp.dtos.CropRecommendationResponse;
import com.SmartAgriculture.Cropp.dtos.CropRequestDTO;
import com.SmartAgriculture.Cropp.model.CropRecommendation;
import com.SmartAgriculture.Cropp.model.SensorData;
import com.SmartAgriculture.Cropp.repository.CropRepository;
import com.SmartAgriculture.Cropp.repository.SensorDataRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CropRecommendationServiceImpl implements CropRecommendationService {

        private final SensorDataRepository sensorDataRepository;
        private final CropRepository cropRepository;
        private final MlPredictionService mlPredictionService;
        private final AiAdvisoryService aiAdvisoryService;
        private final ModelMapper modelMapper;

        @Override
        public CropRecommendationResponse recommendCrop(CropRequestDTO request) {

                SensorData sensorData = new SensorData();
                sensorData.setHumidity(request.getHumidity());
                sensorData.setNitrogen(request.getNitrogen());
                sensorData.setPhosphorus(request.getPhosphorus());
                sensorData.setPotassium(request.getPotassium());
                sensorData.setSoilMoisture(request.getSoilMoisture());
                sensorData.setTemperature(request.getTemperature());
                sensorData.setPh(request.getPh());

                sensorDataRepository.save(sensorData);

                CropRecommendationResponse result = mlPredictionService.predictCrop(sensorData);

                AdvisoryResponse advisory = aiAdvisoryService.generateCropAdvisory(
                                result.getCropName(),
                                sensorData);

                CropRecommendationResponse response = new CropRecommendationResponse();

                response.setCropName(result.getCropName());
                response.setCropConfidence(result.getCropConfidence());
                response.setExplanation(advisory.getExplanation());

                CropRecommendation crop = new CropRecommendation();
                crop.setCropName(response.getCropName());
                crop.setExplanation(response.getExplanation());
                crop.setConfidenceScore(response.getCropConfidence());
                crop.setCropId(response.getCropId());
                crop.setSensorData(sensorData);
                crop.setLocation(request.getLocation());
                CropRecommendation save = cropRepository.save(crop);

                response.setCropId(save.getCropId());

                return response;
        }

        @Override
        public List<CropRecommendationResponse> getCropByName(String name) {
                List<CropRecommendation> crops = cropRepository.findByCropNameIgnoreCase(name);
                if (crops.isEmpty()) {
                        throw new RuntimeException("Crop not Found !");
                }
                List<CropRecommendationResponse> response = crops.stream()
                                .map(crop -> modelMapper.map(crop, CropRecommendationResponse.class))
                                .collect(Collectors.toList());

                return response;
        }
}

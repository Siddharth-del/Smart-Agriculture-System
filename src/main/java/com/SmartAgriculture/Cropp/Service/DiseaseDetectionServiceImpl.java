package com.SmartAgriculture.Cropp.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.SmartAgriculture.Cropp.Service.Ai.AiAdvisoryService;
import com.SmartAgriculture.Cropp.Service.Ai.MlPredictionService;
import com.SmartAgriculture.Cropp.dtos.AdvisoryResponse;
import com.SmartAgriculture.Cropp.dtos.DiseaseDetectionResponse;
import com.SmartAgriculture.Cropp.dtos.DiseasePredictionResponse;
import com.SmartAgriculture.Cropp.model.DiseaseDetection;
import com.SmartAgriculture.Cropp.repository.DiseaseDetectionRepository;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class DiseaseDetectionServiceImpl implements DiseaseDetectionService {

    private final DiseaseDetectionRepository diseaseRepository;
    private final MlPredictionService mlPredictionService;
    private final AiAdvisoryService aiAdvisoryService;
    private final FileService fileService;

    @Value("${project.image}")
    private String imagePath;

    @PostConstruct
    public void init() throws IOException {
        Path path = Paths.get(imagePath);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
            log.info("Created image directory: {}", path.toAbsolutePath());
        }
    }

    @Override
    @Transactional
    public DiseaseDetectionResponse detectdisease(MultipartFile image) throws IOException {
        File savedFile = null;
        String fileName = null;
        
        try {
            fileName = fileService.uploadImage(imagePath, image);
            savedFile = new File(imagePath + File.separator + fileName);
            
            log.info("Image saved: {}", savedFile.getAbsolutePath());

            DiseasePredictionResponse result = mlPredictionService.detectDisease(savedFile);
            
            log.info("Disease detected: {} with confidence: {}", 
                     result.getDiseaseName(), result.getConfidence());

            DiseaseDetection disease = new DiseaseDetection();
            disease.setImagePath(fileName);
            disease.setPredictedDisease(result.getDiseaseName());
            disease.setConfidenceScore(result.getConfidence());
            DiseaseDetection saved = diseaseRepository.save(disease);

            AdvisoryResponse advisory = aiAdvisoryService
                    .generateDiseaseAdvisory(result.getDiseaseName());

            DiseaseDetectionResponse response = new DiseaseDetectionResponse();
            response.setPredicted(List.of(result));
            response.setExplanation(advisory.getExplanation());
            response.setFertilizerRecommendation(advisory.getFertilizerRecommendation());
            response.setPesticideRecommendation(advisory.getPesticideRecommendation());
            response.setGeneratedAt(saved.getCreatedAt());

            return response;
            
        } catch (Exception e) {
            log.error("Error detecting disease", e);
            if (fileName != null) {
                try {
                    fileService.deleteImage(imagePath, fileName);
                    log.info("Cleaned up file after error: {}", fileName);
                } catch (IOException cleanupError) {
                    log.warn("Failed to cleanup file: {}", fileName, cleanupError);
                }
            }
            throw e;
        } finally {
            if (savedFile != null && savedFile.exists()) {
                try {
                    Files.deleteIfExists(savedFile.toPath());
                    log.info("Temporary file deleted: {}", savedFile.getName());
                } catch (IOException e) {
                    log.warn("Failed to delete temporary file: {}", savedFile.getName(), e);
                }
            }
        }
    }
}

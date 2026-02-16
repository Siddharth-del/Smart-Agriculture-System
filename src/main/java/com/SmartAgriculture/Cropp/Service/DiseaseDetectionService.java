package com.SmartAgriculture.Cropp.Service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import com.SmartAgriculture.Cropp.dtos.DiseaseDetectionResponse;
import com.SmartAgriculture.Cropp.model.User;

public interface DiseaseDetectionService {
    DiseaseDetectionResponse detectdisease(MultipartFile image) throws IOException;
     public List<DiseaseDetectionResponse> getDiseaseByName(String diseaseName);
}
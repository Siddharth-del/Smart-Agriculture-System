package com.SmartAgriculture.Cropp.Service;

import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;
import com.SmartAgriculture.Cropp.dtos.DiseaseDetectionResponse;

public interface DiseaseDetectionService {
    DiseaseDetectionResponse detectdisease(MultipartFile image) throws IOException;
}
package com.SmartAgriculture.Cropp.Service.Ai;

import com.SmartAgriculture.Cropp.dtos.MlPredictionResponse;
import com.SmartAgriculture.Cropp.model.SensorData;

public interface MlPredictionService {
    MlPredictionResponse predictCrop(SensorData data);
}

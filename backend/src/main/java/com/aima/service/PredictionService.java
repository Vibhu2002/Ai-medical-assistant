package com.aima.service;

import com.aima.dto.PredictionRequest;
import com.aima.dto.PredictionResponse;

public interface PredictionService {
    PredictionResponse createPrediction(String userEmail, PredictionRequest request);
}

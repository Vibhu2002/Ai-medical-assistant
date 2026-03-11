package com.aima.service.impl;

import com.aima.client.MlClient;
import com.aima.dto.*;
import com.aima.exception.BadRequestException;
import com.aima.model.Prediction;
import com.aima.model.User;
import com.aima.repository.PredictionRepository;
import com.aima.repository.UserRepository;
import com.aima.service.PredictionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PredictionServiceImpl implements PredictionService {

    private final MlClient mlClient;
    private final UserRepository userRepository;
    private final PredictionRepository predictionRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public PredictionResponse createPrediction(String userEmail, PredictionRequest request) {
        if (request.getSymptoms() == null || request.getSymptoms().isEmpty()) {
            throw new BadRequestException("At least one symptom must be provided");
        }

        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new BadRequestException("User not found"));

        MlPredictResponse mlResponse = mlClient.predict(new MlPredictRequest(request.getSymptoms()));

        String payload;
        try {
            payload = objectMapper.writeValueAsString(request.getSymptoms());
        } catch (JsonProcessingException e) {
            throw new BadRequestException("Failed to serialize symptoms");
        }

        Prediction entity = Prediction.builder()
                .user(user)
                .symptomPayload(payload)
                .predictedDisease(mlResponse.getPredictedDisease())
                .confidenceScore(mlResponse.getConfidenceScore())
                .recommendation("Consult a physician for confirmation. Stay hydrated and monitor symptoms.")
                .build();

        predictionRepository.save(entity);

        return PredictionResponse.builder()
                .predictedDisease(entity.getPredictedDisease())
                .confidenceScore(entity.getConfidenceScore())
                .recommendation(entity.getRecommendation())
                .build();
    }
}

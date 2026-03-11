package com.aima.controller;

import com.aima.dto.PredictionRequest;
import com.aima.dto.PredictionResponse;
import com.aima.service.PredictionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PredictionController {

    private final PredictionService predictionService;

    @PostMapping("/predict")
    public PredictionResponse predict(@Valid @RequestBody PredictionRequest request, Authentication authentication) {
        return predictionService.createPrediction(authentication.getName(), request);
    }
}

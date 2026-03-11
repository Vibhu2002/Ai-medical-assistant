package com.aima.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PredictionResponse {
    private String predictedDisease;
    private Double confidenceScore;
    private String recommendation;
}

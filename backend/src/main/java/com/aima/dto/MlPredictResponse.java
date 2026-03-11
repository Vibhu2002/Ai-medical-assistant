package com.aima.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

@Data
public class MlPredictResponse {
    @JsonProperty("predicted_disease")
    private String predictedDisease;

    @JsonProperty("confidence_score")
    private Double confidenceScore;

    private Map<String, Double> probabilities;
}

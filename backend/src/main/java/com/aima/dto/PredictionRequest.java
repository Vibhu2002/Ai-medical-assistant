package com.aima.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Data
public class PredictionRequest {
    @NotEmpty
    private List<String> symptoms;
}

package com.aima.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class MlPredictRequest {
    private List<String> symptoms;
}

package com.aima.service;

import com.aima.model.Disease;
import com.aima.model.Prediction;
import com.aima.model.Symptom;

import java.util.List;

public interface AdminService {
    Disease createDisease(Disease disease);
    Disease updateDisease(Long id, Disease disease);
    void deleteDisease(Long id);
    Symptom createSymptom(Symptom symptom);
    List<Prediction> getPredictionLogs();
}

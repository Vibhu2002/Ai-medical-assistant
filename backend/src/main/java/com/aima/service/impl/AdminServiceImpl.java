package com.aima.service.impl;

import com.aima.exception.BadRequestException;
import com.aima.model.Disease;
import com.aima.model.Prediction;
import com.aima.model.Symptom;
import com.aima.repository.DiseaseRepository;
import com.aima.repository.PredictionRepository;
import com.aima.repository.SymptomRepository;
import com.aima.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final DiseaseRepository diseaseRepository;
    private final SymptomRepository symptomRepository;
    private final PredictionRepository predictionRepository;

    @Override
    public Disease createDisease(Disease disease) {
        return diseaseRepository.save(disease);
    }

    @Override
    public Disease updateDisease(Long id, Disease disease) {
        Disease existing = diseaseRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Disease not found"));
        existing.setName(disease.getName());
        existing.setDescription(disease.getDescription());
        existing.setPrecautions(disease.getPrecautions());
        existing.setSeverity(disease.getSeverity());
        return diseaseRepository.save(existing);
    }

    @Override
    public void deleteDisease(Long id) {
        diseaseRepository.deleteById(id);
    }

    @Override
    public Symptom createSymptom(Symptom symptom) {
        return symptomRepository.save(symptom);
    }

    @Override
    public List<Prediction> getPredictionLogs() {
        return predictionRepository.findAll();
    }
}

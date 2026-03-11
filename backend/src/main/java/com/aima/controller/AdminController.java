package com.aima.controller;

import com.aima.model.Disease;
import com.aima.model.Prediction;
import com.aima.model.Symptom;
import com.aima.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @PostMapping("/diseases")
    public Disease createDisease(@RequestBody Disease disease) {
        return adminService.createDisease(disease);
    }

    @PutMapping("/diseases/{id}")
    public Disease updateDisease(@PathVariable Long id, @RequestBody Disease disease) {
        return adminService.updateDisease(id, disease);
    }

    @DeleteMapping("/diseases/{id}")
    public void deleteDisease(@PathVariable Long id) {
        adminService.deleteDisease(id);
    }

    @PostMapping("/symptoms")
    public Symptom createSymptom(@RequestBody Symptom symptom) {
        return adminService.createSymptom(symptom);
    }

    @GetMapping("/predictions")
    public List<Prediction> predictionLogs() {
        return adminService.getPredictionLogs();
    }
}

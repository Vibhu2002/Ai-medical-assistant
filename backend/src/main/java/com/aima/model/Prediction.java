package com.aima.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "predictions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Prediction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "symptom_payload", nullable = false, columnDefinition = "JSON")
    private String symptomPayload;

    @Column(name = "predicted_disease", nullable = false)
    private String predictedDisease;

    @Column(name = "confidence_score", nullable = false)
    private Double confidenceScore;

    @Column(columnDefinition = "TEXT")
    private String recommendation;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }
}

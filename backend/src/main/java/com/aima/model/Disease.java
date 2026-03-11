package com.aima.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "diseases")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Disease {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(columnDefinition = "TEXT")
    private String precautions;

    private String severity;
}

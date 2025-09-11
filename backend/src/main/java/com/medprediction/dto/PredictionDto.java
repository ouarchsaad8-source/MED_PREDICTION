package com.medprediction.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class PredictionDto {
    private Long id;
    private List<String> symptoms;
    private String description;
    private LocalDate onsetDate;
    private Integer severity;
    private String status;
    private String notes;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<DiseaseDto> diseases;
}

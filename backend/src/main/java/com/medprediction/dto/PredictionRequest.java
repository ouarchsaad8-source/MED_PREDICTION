package com.medprediction.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class PredictionRequest {
    
    @NotEmpty(message = "Symptoms list cannot be empty")
    private List<String> symptoms;
    
    private String description;
    
    private LocalDate onsetDate;
    
    @NotNull(message = "Severity is required")
    @Min(value = 1, message = "Severity must be between 1 and 10")
    @Max(value = 10, message = "Severity must be between 1 and 10")
    private Integer severity;
}

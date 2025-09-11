package com.medprediction.dto;

import lombok.Data;

import java.util.List;

@Data
public class DiseaseDto {
    private Long id;
    private String name;
    private Double probability;
    private String reason;
    private List<MedicationDto> medications;
}

package com.medprediction.dto;

import lombok.Data;

@Data
public class MedicationDto {
    private Long id;
    private String name;
    private String dose;
    private String frequency;
    private String reason;
}

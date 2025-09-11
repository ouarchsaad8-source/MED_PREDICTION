package com.medprediction.dto;

import lombok.Data;

@Data
public class SymptomDto {
    private Long id;
    private String name;
    private String category;
    private String description;
    private Boolean active;
}

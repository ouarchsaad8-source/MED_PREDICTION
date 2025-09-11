package com.medprediction.service;

import com.medprediction.dto.SymptomDto;
import com.medprediction.entity.Symptom;
import com.medprediction.repository.SymptomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SymptomService {

    private final SymptomRepository symptomRepository;

    public List<SymptomDto> getAllActiveSymptoms() {
        List<Symptom> symptoms = symptomRepository.findByActiveTrue();
        return symptoms.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    public List<SymptomDto> getSymptomsByCategory(String category) {
        List<Symptom> symptoms = symptomRepository.findByCategoryAndActiveTrue(category);
        return symptoms.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    public List<String> getAllCategories() {
        return symptomRepository.findAllCategories();
    }

    public List<SymptomDto> searchSymptoms(String keyword) {
        List<Symptom> symptoms = symptomRepository.findByNameContainingIgnoreCaseAndActiveTrue(keyword);
        return symptoms.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    private SymptomDto mapToDto(Symptom symptom) {
        SymptomDto dto = new SymptomDto();
        dto.setId(symptom.getId());
        dto.setName(symptom.getName());
        dto.setCategory(symptom.getCategory());
        dto.setDescription(symptom.getDescription());
        dto.setActive(symptom.getActive());
        return dto;
    }
}

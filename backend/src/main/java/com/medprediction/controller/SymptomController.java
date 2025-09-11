package com.medprediction.controller;

import com.medprediction.dto.SymptomDto;
import com.medprediction.service.SymptomService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/symptoms")
@RequiredArgsConstructor
@Tag(name = "Symptoms", description = "Symptom management endpoints")
public class SymptomController {

    private final SymptomService symptomService;

    @GetMapping("/public/all")
    @Operation(summary = "Get all active symptoms", description = "Retrieve all active symptoms available for selection")
    public ResponseEntity<List<SymptomDto>> getAllActiveSymptoms() {
        List<SymptomDto> symptoms = symptomService.getAllActiveSymptoms();
        return ResponseEntity.ok(symptoms);
    }

    @GetMapping("/public/categories")
    @Operation(summary = "Get symptom categories", description = "Retrieve all available symptom categories")
    public ResponseEntity<List<String>> getAllCategories() {
        List<String> categories = symptomService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/public/category/{category}")
    @Operation(summary = "Get symptoms by category", description = "Retrieve symptoms filtered by category")
    public ResponseEntity<List<SymptomDto>> getSymptomsByCategory(@PathVariable String category) {
        List<SymptomDto> symptoms = symptomService.getSymptomsByCategory(category);
        return ResponseEntity.ok(symptoms);
    }

    @GetMapping("/public/search")
    @Operation(summary = "Search symptoms", description = "Search symptoms by keyword")
    public ResponseEntity<List<SymptomDto>> searchSymptoms(@RequestParam String keyword) {
        List<SymptomDto> symptoms = symptomService.searchSymptoms(keyword);
        return ResponseEntity.ok(symptoms);
    }
}

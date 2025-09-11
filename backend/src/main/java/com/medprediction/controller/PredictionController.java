package com.medprediction.controller;

import com.medprediction.dto.PredictionDto;
import com.medprediction.dto.PredictionRequest;
import com.medprediction.service.PredictionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/predictions")
@RequiredArgsConstructor
@Tag(name = "Predictions", description = "Medical prediction endpoints")
public class PredictionController {

    private final PredictionService predictionService;

    @PostMapping
    @Operation(summary = "Create new prediction", description = "Submit symptoms for AI analysis and get disease predictions")
    public ResponseEntity<PredictionDto> createPrediction(@Valid @RequestBody PredictionRequest request) {
        PredictionDto prediction = predictionService.createPrediction(request);
        return ResponseEntity.ok(prediction);
    }

    @GetMapping
    @Operation(summary = "Get user predictions", description = "Retrieve all predictions for the authenticated user")
    public ResponseEntity<List<PredictionDto>> getUserPredictions() {
        List<PredictionDto> predictions = predictionService.getUserPredictions();
        return ResponseEntity.ok(predictions);
    }

    @GetMapping("/recent")
    @Operation(summary = "Get recent predictions", description = "Retrieve recent predictions with optional limit")
    public ResponseEntity<List<PredictionDto>> getRecentPredictions(@RequestParam(defaultValue = "5") int limit) {
        List<PredictionDto> predictions = predictionService.getRecentPredictions(limit);
        return ResponseEntity.ok(predictions);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get prediction by ID", description = "Retrieve a specific prediction by its ID")
    public ResponseEntity<PredictionDto> getPredictionById(@PathVariable Long id) {
        PredictionDto prediction = predictionService.getPredictionById(id);
        return ResponseEntity.ok(prediction);
    }
}

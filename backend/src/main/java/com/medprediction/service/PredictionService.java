package com.medprediction.service;

import com.medprediction.dto.*;
import com.medprediction.entity.*;
import com.medprediction.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class PredictionService {

    private final PredictionRepository predictionRepository;
    private final DiseaseRepository diseaseRepository;
    private final MedicationRepository medicationRepository;
    private final UserService userService;

    public PredictionDto createPrediction(PredictionRequest request) {
        User currentUser = userService.getCurrentUser();
        
        Prediction prediction = new Prediction();
        prediction.setUser(currentUser);
        prediction.setSymptoms(request.getSymptoms());
        prediction.setDescription(request.getDescription());
        prediction.setOnsetDate(request.getOnsetDate());
        prediction.setSeverity(request.getSeverity());
        prediction.setStatus(Prediction.Status.PROCESSING);
        
        prediction = predictionRepository.save(prediction);
        
        // Simulate AI prediction logic
        List<Disease> diseases = simulateAIPrediction(prediction, request.getSymptoms(), request.getSeverity());
        
        prediction.setStatus(Prediction.Status.COMPLETED);
        prediction = predictionRepository.save(prediction);
        
        return mapToDto(prediction);
    }

    public List<PredictionDto> getUserPredictions() {
        User currentUser = userService.getCurrentUser();
        List<Prediction> predictions = predictionRepository.findByUserIdOrderByCreatedAtDesc(currentUser.getId());
        return predictions.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    public List<PredictionDto> getRecentPredictions(int limit) {
        User currentUser = userService.getCurrentUser();
        List<Prediction> predictions = predictionRepository.findRecentPredictionsByUserId(
            currentUser.getId(), PageRequest.of(0, limit));
        return predictions.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    public PredictionDto getPredictionById(Long id) {
        User currentUser = userService.getCurrentUser();
        Prediction prediction = predictionRepository.findByIdAndUserId(id, currentUser.getId())
            .orElseThrow(() -> new RuntimeException("Prediction not found"));
        return mapToDto(prediction);
    }

    private List<Disease> simulateAIPrediction(Prediction prediction, List<String> symptoms, Integer severity) {
        // Static simulation data based on symptoms
        Map<String, List<DiseaseData>> symptomDiseaseMap = createSymptomDiseaseMap();
        
        Map<String, Double> diseaseScores = new HashMap<>();
        
        // Calculate disease probabilities based on symptoms
        for (String symptom : symptoms) {
            List<DiseaseData> relatedDiseases = symptomDiseaseMap.getOrDefault(symptom.toLowerCase(), new ArrayList<>());
            for (DiseaseData diseaseData : relatedDiseases) {
                diseaseScores.merge(diseaseData.name, diseaseData.baseScore, Double::sum);
            }
        }
        
        // Adjust scores based on severity
        double severityMultiplier = 0.5 + (severity * 0.05);
        diseaseScores.replaceAll((k, v) -> Math.min(0.95, v * severityMultiplier));
        
        // Sort by probability and take top 5
        List<Map.Entry<String, Double>> sortedDiseases = diseaseScores.entrySet().stream()
            .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
            .limit(5)
            .collect(Collectors.toList());
        
        List<Disease> diseases = new ArrayList<>();
        for (Map.Entry<String, Double> entry : sortedDiseases) {
            Disease disease = new Disease();
            disease.setPrediction(prediction);
            disease.setName(entry.getKey());
            disease.setProbability(entry.getValue());
            disease.setReason(generateReason(entry.getKey(), symptoms));
            
            disease = diseaseRepository.save(disease);
            
            // Add medications for each disease
            List<Medication> medications = generateMedications(disease);
            disease.setMedications(medications);
            
            diseases.add(disease);
        }
        
        return diseases;
    }

    private Map<String, List<DiseaseData>> createSymptomDiseaseMap() {
        Map<String, List<DiseaseData>> map = new HashMap<>();
        
        // Respiratory symptoms
        map.put("cough", Arrays.asList(
            new DiseaseData("Common Cold", 0.25),
            new DiseaseData("Bronchitis", 0.20),
            new DiseaseData("Pneumonia", 0.15),
            new DiseaseData("COVID-19", 0.18)
        ));
        
        map.put("fever", Arrays.asList(
            new DiseaseData("Influenza", 0.30),
            new DiseaseData("COVID-19", 0.25),
            new DiseaseData("Pneumonia", 0.20),
            new DiseaseData("Urinary Tract Infection", 0.15)
        ));
        
        map.put("headache", Arrays.asList(
            new DiseaseData("Tension Headache", 0.35),
            new DiseaseData("Migraine", 0.25),
            new DiseaseData("Sinusitis", 0.20),
            new DiseaseData("Influenza", 0.15)
        ));
        
        map.put("fatigue", Arrays.asList(
            new DiseaseData("Anemia", 0.20),
            new DiseaseData("Depression", 0.18),
            new DiseaseData("Thyroid Disorder", 0.15),
            new DiseaseData("Chronic Fatigue Syndrome", 0.12)
        ));
        
        map.put("nausea", Arrays.asList(
            new DiseaseData("Gastroenteritis", 0.30),
            new DiseaseData("Food Poisoning", 0.25),
            new DiseaseData("Migraine", 0.15),
            new DiseaseData("Pregnancy", 0.10)
        ));
        
        return map;
    }

    private String generateReason(String diseaseName, List<String> symptoms) {
        return String.format("Based on the combination of symptoms: %s, %s is a likely diagnosis.", 
            String.join(", ", symptoms), diseaseName);
    }

    private List<Medication> generateMedications(Disease disease) {
        Map<String, List<MedicationData>> diseaseMedicationMap = createDiseaseMedicationMap();
        List<MedicationData> medicationDataList = diseaseMedicationMap.getOrDefault(
            disease.getName(), new ArrayList<>());
        
        List<Medication> medications = new ArrayList<>();
        for (MedicationData medData : medicationDataList) {
            Medication medication = new Medication();
            medication.setDisease(disease);
            medication.setName(medData.name);
            medication.setDose(medData.dose);
            medication.setFrequency(medData.frequency);
            medication.setReason(medData.reason);
            
            medications.add(medicationRepository.save(medication));
        }
        
        return medications;
    }

    private Map<String, List<MedicationData>> createDiseaseMedicationMap() {
        Map<String, List<MedicationData>> map = new HashMap<>();
        
        map.put("Common Cold", Arrays.asList(
            new MedicationData("Acetaminophen", "500mg", "Every 6 hours", "Pain and fever relief"),
            new MedicationData("Dextromethorphan", "15mg", "Every 4 hours", "Cough suppression")
        ));
        
        map.put("Influenza", Arrays.asList(
            new MedicationData("Oseltamivir", "75mg", "Twice daily", "Antiviral treatment"),
            new MedicationData("Ibuprofen", "400mg", "Every 8 hours", "Fever and body aches")
        ));
        
        map.put("Migraine", Arrays.asList(
            new MedicationData("Sumatriptan", "50mg", "As needed", "Migraine-specific treatment"),
            new MedicationData("Ibuprofen", "600mg", "Every 8 hours", "Pain relief")
        ));
        
        return map;
    }

    private PredictionDto mapToDto(Prediction prediction) {
        PredictionDto dto = new PredictionDto();
        dto.setId(prediction.getId());
        dto.setSymptoms(prediction.getSymptoms());
        dto.setDescription(prediction.getDescription());
        dto.setOnsetDate(prediction.getOnsetDate());
        dto.setSeverity(prediction.getSeverity());
        dto.setStatus(prediction.getStatus().name());
        dto.setNotes(prediction.getNotes());
        dto.setCreatedAt(prediction.getCreatedAt());
        dto.setUpdatedAt(prediction.getUpdatedAt());
        
        if (prediction.getDiseases() != null) {
            dto.setDiseases(prediction.getDiseases().stream()
                .map(this::mapDiseaseToDto)
                .collect(Collectors.toList()));
        }
        
        return dto;
    }

    private DiseaseDto mapDiseaseToDto(Disease disease) {
        DiseaseDto dto = new DiseaseDto();
        dto.setId(disease.getId());
        dto.setName(disease.getName());
        dto.setProbability(disease.getProbability());
        dto.setReason(disease.getReason());
        
        if (disease.getMedications() != null) {
            dto.setMedications(disease.getMedications().stream()
                .map(this::mapMedicationToDto)
                .collect(Collectors.toList()));
        }
        
        return dto;
    }

    private MedicationDto mapMedicationToDto(Medication medication) {
        MedicationDto dto = new MedicationDto();
        dto.setId(medication.getId());
        dto.setName(medication.getName());
        dto.setDose(medication.getDose());
        dto.setFrequency(medication.getFrequency());
        dto.setReason(medication.getReason());
        return dto;
    }

    private static class DiseaseData {
        String name;
        Double baseScore;
        
        DiseaseData(String name, Double baseScore) {
            this.name = name;
            this.baseScore = baseScore;
        }
    }

    private static class MedicationData {
        String name;
        String dose;
        String frequency;
        String reason;
        
        MedicationData(String name, String dose, String frequency, String reason) {
            this.name = name;
            this.dose = dose;
            this.frequency = frequency;
            this.reason = reason;
        }
    }
}

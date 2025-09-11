package com.medprediction.repository;

import com.medprediction.entity.Medication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicationRepository extends JpaRepository<Medication, Long> {

    List<Medication> findByDiseaseId(Long diseaseId);

    @Query("SELECT m FROM Medication m WHERE m.disease.prediction.id = :predictionId")
    List<Medication> findByPredictionId(@Param("predictionId") Long predictionId);

    @Query("SELECT DISTINCT m.name FROM Medication m ORDER BY m.name")
    List<String> findAllMedicationNames();

    @Query("SELECT m FROM Medication m WHERE m.disease.prediction.user.id = :userId")
    List<Medication> findByUserId(@Param("userId") Long userId);
}

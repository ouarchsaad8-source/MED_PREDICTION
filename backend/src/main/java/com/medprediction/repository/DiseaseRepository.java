package com.medprediction.repository;

import com.medprediction.entity.Disease;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiseaseRepository extends JpaRepository<Disease, Long> {

    List<Disease> findByPredictionIdOrderByProbabilityDesc(Long predictionId);

    @Query("SELECT d FROM Disease d WHERE d.prediction.id = :predictionId AND d.probability >= :minProbability ORDER BY d.probability DESC")
    List<Disease> findByPredictionIdAndMinProbability(@Param("predictionId") Long predictionId, @Param("minProbability") Double minProbability);

    @Query("SELECT DISTINCT d.name FROM Disease d ORDER BY d.name")
    List<String> findAllDiseaseNames();

    @Query("SELECT COUNT(d) FROM Disease d WHERE d.prediction.user.id = :userId")
    Long countByUserId(@Param("userId") Long userId);
}

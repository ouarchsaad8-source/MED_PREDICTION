package com.medprediction.repository;

import com.medprediction.entity.Prediction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface PredictionRepository extends JpaRepository<Prediction, Long> {

    List<Prediction> findByUserIdOrderByCreatedAtDesc(Long userId);

    Page<Prediction> findByUserIdOrderByCreatedAtDesc(Long userId, Pageable pageable);

    @Query("SELECT p FROM Prediction p WHERE p.user.id = :userId AND p.id = :predictionId")
    Optional<Prediction> findByIdAndUserId(@Param("predictionId") Long predictionId, @Param("userId") Long userId);

    @Query("SELECT p FROM Prediction p WHERE p.user.id = :userId ORDER BY p.createdAt DESC")
    List<Prediction> findRecentPredictionsByUserId(@Param("userId") Long userId, Pageable pageable);

    @Query("SELECT p FROM Prediction p WHERE p.createdAt >= :startDate AND p.createdAt <= :endDate ORDER BY p.createdAt DESC")
    List<Prediction> findPredictionsBetweenDates(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    List<Prediction> findByStatus(Prediction.Status status);

    @Query("SELECT COUNT(p) FROM Prediction p WHERE p.user.id = :userId AND p.status = :status")
    Long countByUserIdAndStatus(@Param("userId") Long userId, @Param("status") Prediction.Status status);
}

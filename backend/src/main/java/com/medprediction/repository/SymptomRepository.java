package com.medprediction.repository;

import com.medprediction.entity.Symptom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SymptomRepository extends JpaRepository<Symptom, Long> {

    List<Symptom> findByActiveTrue();

    List<Symptom> findByCategoryAndActiveTrue(String category);

    @Query("SELECT DISTINCT s.category FROM Symptom s WHERE s.active = true ORDER BY s.category")
    List<String> findAllCategories();

    @Query("SELECT s FROM Symptom s WHERE s.name LIKE %:keyword% AND s.active = true")
    List<Symptom> findByNameContainingIgnoreCaseAndActiveTrue(@Param("keyword") String keyword);

    boolean existsByNameAndActiveTrue(String name);
}

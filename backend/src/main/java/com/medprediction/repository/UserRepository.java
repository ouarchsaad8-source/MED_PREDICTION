package com.medprediction.repository;

import com.medprediction.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.email = :email AND u.active = true")
    Optional<User> findActiveUserByEmail(@Param("email") String email);

    @Query("SELECT COUNT(p) FROM Prediction p WHERE p.user.id = :userId AND MONTH(p.createdAt) = MONTH(CURRENT_DATE) AND YEAR(p.createdAt) = YEAR(CURRENT_DATE)")
    Long countPredictionsThisMonth(@Param("userId") Long userId);

    @Query("SELECT COUNT(p) FROM Prediction p WHERE p.user.id = :userId AND p.status = 'COMPLETED'")
    Long countCompletedPredictions(@Param("userId") Long userId);

    @Query("SELECT COUNT(p) FROM Prediction p WHERE p.user.id = :userId")
    Long countTotalPredictions(@Param("userId") Long userId);
}

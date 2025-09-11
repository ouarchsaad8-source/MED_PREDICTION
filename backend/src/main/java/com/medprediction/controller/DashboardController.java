package com.medprediction.controller;

import com.medprediction.repository.UserRepository;
import com.medprediction.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/dashboard")
@RequiredArgsConstructor
@Tag(name = "Dashboard", description = "Dashboard statistics and data")
public class DashboardController {

    private final UserRepository userRepository;
    private final UserService userService;

    @GetMapping("/stats")
    @Operation(summary = "Get dashboard statistics", description = "Retrieve user-specific dashboard statistics")
    public ResponseEntity<Map<String, Object>> getDashboardStats() {
        Long userId = userService.getCurrentUser().getId();
        
        Map<String, Object> stats = new HashMap<>();
        stats.put("totalPredictions", userRepository.countTotalPredictions(userId));
        stats.put("completedPredictions", userRepository.countCompletedPredictions(userId));
        stats.put("predictionsThisMonth", userRepository.countPredictionsThisMonth(userId));
        stats.put("accuracy", 94.5); // Static value for demo
        
        return ResponseEntity.ok(stats);
    }
}

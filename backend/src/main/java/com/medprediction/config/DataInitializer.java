package com.medprediction.config;

import com.medprediction.entity.Symptom;
import com.medprediction.entity.User;
import com.medprediction.repository.SymptomRepository;
import com.medprediction.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final SymptomRepository symptomRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        log.info("Initializing database with sample data...");
        
        initializeUsers();
        initializeSymptoms();
        
        log.info("Database initialization completed.");
    }

    private void initializeUsers() {
        if (userRepository.count() == 0) {
            log.info("Creating sample users...");
            
            // Admin user
            User admin = new User();
            admin.setFirstName("Admin");
            admin.setLastName("User");
            admin.setEmail("admin@medprediction.com");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRole(User.Role.ADMIN);
            admin.setActive(true);
            admin.setEmailNotifications(true);
            admin.setSmsNotifications(false);
            userRepository.save(admin);

            // Demo user
            User demoUser = new User();
            demoUser.setFirstName("John");
            demoUser.setLastName("Doe");
            demoUser.setEmail("john.doe@example.com");
            demoUser.setPassword(passwordEncoder.encode("password123"));
            demoUser.setPhone("+1234567890");
            demoUser.setDateOfBirth(LocalDate.of(1990, 5, 15));
            demoUser.setGender(User.Gender.MALE);
            demoUser.setRole(User.Role.USER);
            demoUser.setActive(true);
            demoUser.setEmailNotifications(true);
            demoUser.setSmsNotifications(true);
            userRepository.save(demoUser);

            // Another demo user
            User demoUser2 = new User();
            demoUser2.setFirstName("Jane");
            demoUser2.setLastName("Smith");
            demoUser2.setEmail("jane.smith@example.com");
            demoUser2.setPassword(passwordEncoder.encode("password123"));
            demoUser2.setPhone("+1987654321");
            demoUser2.setDateOfBirth(LocalDate.of(1985, 8, 22));
            demoUser2.setGender(User.Gender.FEMALE);
            demoUser2.setRole(User.Role.USER);
            demoUser2.setActive(true);
            demoUser2.setEmailNotifications(true);
            demoUser2.setSmsNotifications(false);
            userRepository.save(demoUser2);

            log.info("Sample users created successfully.");
        }
    }

    private void initializeSymptoms() {
        if (symptomRepository.count() == 0) {
            log.info("Creating symptom database...");
            
            List<SymptomData> symptoms = Arrays.asList(
                // Respiratory
                new SymptomData("Cough", "Respiratory", "Persistent or occasional coughing"),
                new SymptomData("Shortness of breath", "Respiratory", "Difficulty breathing or feeling breathless"),
                new SymptomData("Chest pain", "Respiratory", "Pain or discomfort in the chest area"),
                new SymptomData("Wheezing", "Respiratory", "High-pitched whistling sound when breathing"),
                new SymptomData("Sore throat", "Respiratory", "Pain or irritation in the throat"),
                new SymptomData("Runny nose", "Respiratory", "Nasal discharge or congestion"),
                new SymptomData("Sneezing", "Respiratory", "Involuntary expulsion of air from nose"),

                // Gastrointestinal
                new SymptomData("Nausea", "Gastrointestinal", "Feeling of sickness with inclination to vomit"),
                new SymptomData("Vomiting", "Gastrointestinal", "Forceful expulsion of stomach contents"),
                new SymptomData("Diarrhea", "Gastrointestinal", "Loose or watery bowel movements"),
                new SymptomData("Constipation", "Gastrointestinal", "Difficulty passing stools"),
                new SymptomData("Abdominal pain", "Gastrointestinal", "Pain in the stomach or belly area"),
                new SymptomData("Bloating", "Gastrointestinal", "Feeling of fullness or swelling in abdomen"),
                new SymptomData("Loss of appetite", "Gastrointestinal", "Reduced desire to eat"),

                // Neurological
                new SymptomData("Headache", "Neurological", "Pain in the head or upper neck"),
                new SymptomData("Dizziness", "Neurological", "Feeling unsteady or lightheaded"),
                new SymptomData("Confusion", "Neurological", "Difficulty thinking clearly"),
                new SymptomData("Memory problems", "Neurological", "Difficulty remembering things"),
                new SymptomData("Seizures", "Neurological", "Sudden electrical activity in the brain"),
                new SymptomData("Numbness", "Neurological", "Loss of sensation in body parts"),
                new SymptomData("Tingling", "Neurological", "Pins and needles sensation"),

                // Musculoskeletal
                new SymptomData("Joint pain", "Musculoskeletal", "Pain in joints or connecting tissues"),
                new SymptomData("Muscle pain", "Musculoskeletal", "Pain or aching in muscles"),
                new SymptomData("Back pain", "Musculoskeletal", "Pain in the back area"),
                new SymptomData("Stiffness", "Musculoskeletal", "Difficulty moving joints or muscles"),
                new SymptomData("Swelling", "Musculoskeletal", "Enlargement of body parts due to fluid"),
                new SymptomData("Weakness", "Musculoskeletal", "Reduced strength in muscles"),

                // General
                new SymptomData("Fever", "General", "Elevated body temperature"),
                new SymptomData("Fatigue", "General", "Extreme tiredness or exhaustion"),
                new SymptomData("Chills", "General", "Feeling cold with shivering"),
                new SymptomData("Night sweats", "General", "Excessive sweating during sleep"),
                new SymptomData("Weight loss", "General", "Unintentional reduction in body weight"),
                new SymptomData("Weight gain", "General", "Unintentional increase in body weight"),
                new SymptomData("Sleep problems", "General", "Difficulty falling or staying asleep"),

                // Cardiovascular
                new SymptomData("Heart palpitations", "Cardiovascular", "Feeling of irregular heartbeat"),
                new SymptomData("Chest tightness", "Cardiovascular", "Feeling of pressure in chest"),
                new SymptomData("Rapid heartbeat", "Cardiovascular", "Faster than normal heart rate"),
                new SymptomData("Slow heartbeat", "Cardiovascular", "Slower than normal heart rate"),
                new SymptomData("Leg swelling", "Cardiovascular", "Fluid retention in lower extremities"),

                // Dermatological
                new SymptomData("Rash", "Dermatological", "Skin irritation or eruption"),
                new SymptomData("Itching", "Dermatological", "Desire to scratch the skin"),
                new SymptomData("Dry skin", "Dermatological", "Lack of moisture in skin"),
                new SymptomData("Bruising", "Dermatological", "Discoloration from blood under skin"),
                new SymptomData("Hair loss", "Dermatological", "Loss of hair from scalp or body"),

                // Psychological
                new SymptomData("Anxiety", "Psychological", "Feeling of worry or unease"),
                new SymptomData("Depression", "Psychological", "Persistent feeling of sadness"),
                new SymptomData("Mood changes", "Psychological", "Fluctuations in emotional state"),
                new SymptomData("Irritability", "Psychological", "Easily annoyed or made angry"),
                new SymptomData("Panic attacks", "Psychological", "Sudden episodes of intense fear")
            );

            for (SymptomData symptomData : symptoms) {
                Symptom symptom = new Symptom();
                symptom.setName(symptomData.name);
                symptom.setCategory(symptomData.category);
                symptom.setDescription(symptomData.description);
                symptom.setActive(true);
                symptomRepository.save(symptom);
            }

            log.info("Symptom database created with {} symptoms.", symptoms.size());
        }
    }

    private static class SymptomData {
        String name;
        String category;
        String description;

        SymptomData(String name, String category, String description) {
            this.name = name;
            this.category = category;
            this.description = description;
        }
    }
}

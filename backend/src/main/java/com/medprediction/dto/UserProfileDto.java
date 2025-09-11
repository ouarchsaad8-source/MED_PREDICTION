package com.medprediction.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserProfileDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private LocalDate dateOfBirth;
    private String gender;
    private String profilePicture;
    private Boolean emailNotifications;
    private Boolean smsNotifications;
    private String role;
    private Boolean active;
}

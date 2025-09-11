package com.medprediction.service;

import com.medprediction.dto.UserProfileDto;
import com.medprediction.entity.User;
import com.medprediction.repository.UserRepository;
import com.medprediction.security.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserProfileDto getCurrentUserProfile() {
        User user = getCurrentUser();
        return mapToDto(user);
    }

    public UserProfileDto updateProfile(UserProfileDto profileDto) {
        User user = getCurrentUser();
        
        user.setFirstName(profileDto.getFirstName());
        user.setLastName(profileDto.getLastName());
        user.setPhone(profileDto.getPhone());
        user.setDateOfBirth(profileDto.getDateOfBirth());
        
        if (profileDto.getGender() != null) {
            user.setGender(User.Gender.valueOf(profileDto.getGender().toUpperCase()));
        }
        
        user.setEmailNotifications(profileDto.getEmailNotifications());
        user.setSmsNotifications(profileDto.getSmsNotifications());
        
        user = userRepository.save(user);
        return mapToDto(user);
    }

    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsServiceImpl.CustomUserPrincipal principal = 
            (UserDetailsServiceImpl.CustomUserPrincipal) authentication.getPrincipal();
        return principal.getUser();
    }

    private UserProfileDto mapToDto(User user) {
        UserProfileDto dto = new UserProfileDto();
        dto.setId(user.getId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        dto.setPhone(user.getPhone());
        dto.setDateOfBirth(user.getDateOfBirth());
        dto.setGender(user.getGender() != null ? user.getGender().name() : null);
        dto.setProfilePicture(user.getProfilePicture());
        dto.setEmailNotifications(user.getEmailNotifications());
        dto.setSmsNotifications(user.getSmsNotifications());
        dto.setRole(user.getRole().name());
        dto.setActive(user.getActive());
        return dto;
    }
}

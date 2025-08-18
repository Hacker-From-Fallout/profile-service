package com.ignat.chernyshov.profile.services;

import java.time.LocalDate;

import com.ignat.chernyshov.profile.domain.dto.CustomerProfileCreateDto;
import com.ignat.chernyshov.profile.domain.dto.CustomerProfileUpdateDto;
import com.ignat.chernyshov.profile.domain.entities.CustomerProfile;
import com.ignat.chernyshov.profile.domain.entities.Gender;

public interface CustomerProfileService {
    CustomerProfile findById(Long id);
    CustomerProfile findByUsername(String username);
    CustomerProfile findByEmail(String email);
    CustomerProfile findByPhoneNumber(String phoneNumber);

    CustomerProfile createProfile(CustomerProfileCreateDto dto);
    CustomerProfile updateProfile(Long id, CustomerProfileUpdateDto dto);

    void updateUsername(Long id, String username);
    void updateEmail(Long id, String email);
    void updatePhoneNumber(Long id, String phoneNumber);
    void updateFirstName(Long id, String firstName);
    void updateLastName(Long id, String lastName);
    void updateAboutMyself(Long id, String aboutMyself);
    void updateDateOfBirth(Long id, LocalDate dateOfBirth);
    void updateGender(Long id, Gender gender);
    void updateAvatarUrl(Long id, String avatarUrl);

    void deleteProfile(Long id);
}

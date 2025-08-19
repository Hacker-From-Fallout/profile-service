package com.ignat.chernyshov.profile.services;

import java.time.LocalDate;

import com.ignat.chernyshov.profile.domain.dto.SellerProfileUpdateDto;
import com.ignat.chernyshov.profile.domain.dto.kafka.SellerProfileCreateDto;
import com.ignat.chernyshov.profile.domain.entities.Gender;
import com.ignat.chernyshov.profile.domain.entities.SellerProfile;

public interface SellerProfileService {
    SellerProfile findById(Long id);
    SellerProfile findByUsername(String username);
    SellerProfile findByEmail(String email);
    SellerProfile findByPhoneNumber(String phoneNumber);

    SellerProfile createProfile(SellerProfileCreateDto dto);
    SellerProfile updateProfile(Long id, SellerProfileUpdateDto dto);

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

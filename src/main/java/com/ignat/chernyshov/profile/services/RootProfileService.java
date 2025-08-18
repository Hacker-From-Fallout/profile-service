package com.ignat.chernyshov.profile.services;

import java.time.LocalDate;

import com.ignat.chernyshov.profile.domain.dto.RootProfileCreateDto;
import com.ignat.chernyshov.profile.domain.dto.RootProfileUpdateDto;
import com.ignat.chernyshov.profile.domain.entities.Gender;
import com.ignat.chernyshov.profile.domain.entities.RootProfile;

public interface RootProfileService {
    RootProfile findById(Long id);
    RootProfile findByUsername(String username);
    RootProfile findByEmail(String email);
    RootProfile findByPhoneNumber(String phoneNumber);

    RootProfile createProfile(RootProfileCreateDto dto);
    RootProfile updateProfile(Long id, RootProfileUpdateDto dto);

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

package com.ignat.chernyshov.profile.services;

import java.time.LocalDate;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ignat.chernyshov.profile.domain.dto.RootProfileCreateDto;
import com.ignat.chernyshov.profile.domain.dto.RootProfileUpdateDto;
import com.ignat.chernyshov.profile.domain.entities.Gender;
import com.ignat.chernyshov.profile.domain.entities.RootProfile;
import com.ignat.chernyshov.profile.exception.exceptions.ProfileNotFoundException;
import com.ignat.chernyshov.profile.repositories.RootProfileRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DefaultRootProfileService implements RootProfileService {

    private final RootProfileRepository rootProfileRepository;

    @Override
    @Transactional(readOnly = true)
    public RootProfile findById(Long id) {
        return rootProfileRepository.findById(id)
                .orElseThrow(() -> new ProfileNotFoundException("Профиль не найден с id: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public RootProfile findByUsername(String username) {
        return rootProfileRepository.findByUsername(username)
                .orElseThrow(() -> new ProfileNotFoundException("Профиль не найден с username: " + username));
    }

    @Override
    @Transactional(readOnly = true)
    public RootProfile findByEmail(String email) {
        return rootProfileRepository.findByEmail(email)
                .orElseThrow(() -> new ProfileNotFoundException("Профиль не найден с email: " + email));
    }

    @Override
    @Transactional(readOnly = true)
    public RootProfile findByPhoneNumber(String phoneNumber) {
        return rootProfileRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new ProfileNotFoundException("Профиль не найден с phoneNumber: " + phoneNumber));
    }

    @Override
    @Transactional
    public RootProfile createProfile(RootProfileCreateDto dto) {
        RootProfile rootProfile = RootProfile.builder()
                .username(dto.username())
                .email(dto.email())
                .phoneNumber(dto.phoneNumber())
                .firstName(dto.firstName())
                .lastName(dto.lastname())
                .build();

        return rootProfileRepository.save(rootProfile);
    }

    @Override
    @Transactional
    public RootProfile updateProfile(Long id, RootProfileUpdateDto dto) {
        RootProfile rootProfile = rootProfileRepository.findById(id)
            .orElseThrow(() -> new ProfileNotFoundException("Профиль не найден с id: " + id));
        
        if (dto.username() != null) {
            rootProfile.setUsername(dto.username());
        }
        if (dto.email() != null) {
            rootProfile.setEmail(dto.email());
        }
        if (dto.phoneNumber() != null) {
            rootProfile.setPhoneNumber(dto.phoneNumber());
        }
        if (dto.firstName() != null) {
            rootProfile.setFirstName(dto.firstName());
        }
        if (dto.lastName() != null) {
            rootProfile.setLastName(dto.lastName());
        }
        if (dto.aboutMyself() != null) {
            rootProfile.setAboutMyself(dto.aboutMyself());
        }
        if (dto.dateOfBirth() != null) {
            rootProfile.setDateOfBirth(dto.dateOfBirth());
        }
        if (dto.gender() != null) {
            rootProfile.setGender(dto.gender());
        }
        if (dto.avatarUrl() != null) {
            rootProfile.setAvatarUrl(dto.avatarUrl());
        }

        return rootProfileRepository.save(rootProfile);
    }

    @Override
    @Transactional
    public void updateUsername(Long id, String username) {
        ensureUserExists(id);
        rootProfileRepository.updateUsername(id, username);
    }

    @Override
    @Transactional
    public void updateEmail(Long id, String email) {
        ensureUserExists(id);
        rootProfileRepository.updateEmail(id, email);
    }

    @Override
    @Transactional
    public void updatePhoneNumber(Long id, String phoneNumber) {
        ensureUserExists(id);
        rootProfileRepository.updatePhoneNumber(id, phoneNumber);
    }

    @Override
    @Transactional
    public void updateFirstName(Long id, String firstName) {
        ensureUserExists(id);
        rootProfileRepository.updateFirstName(id, firstName);
    }

    @Override
    @Transactional
    public void updateLastName(Long id, String lastName) {
        ensureUserExists(id);
        rootProfileRepository.updateLastName(id, lastName);
    }

    @Override
    @Transactional
    public void updateAboutMyself(Long id, String aboutMyself) {
        ensureUserExists(id);
        rootProfileRepository.updateAboutMyself(id, aboutMyself);
    }

    @Override
    @Transactional
    public void updateDateOfBirth(Long id, LocalDate dateOfBirth) {
        ensureUserExists(id);
        rootProfileRepository.updateDateOfBirth(id, dateOfBirth);
    }

    @Override
    @Transactional
    public void updateGender(Long id, Gender gender) {
        ensureUserExists(id);
        rootProfileRepository.updateGender(id, gender.name());
    }

    @Override
    @Transactional
    public void updateAvatarUrl(Long id, String avatarUrl) {
        ensureUserExists(id);
        rootProfileRepository.updateAvatarUrl(id, avatarUrl);
    }

    @Override
    public void deleteProfile(Long id) {
        rootProfileRepository.deleteById(id);
    }

    private void ensureUserExists(Long id) {
        if (!rootProfileRepository.existsById(id)) {
            throw new ProfileNotFoundException("Профиль не найден с id: " + id);
        }
    }
}

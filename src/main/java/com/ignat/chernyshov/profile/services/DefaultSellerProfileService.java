package com.ignat.chernyshov.profile.services;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ignat.chernyshov.profile.domain.dto.SellerProfileFilterDto;
import com.ignat.chernyshov.profile.domain.dto.SellerProfileUpdateDto;
import com.ignat.chernyshov.profile.domain.dto.kafka.SellerProfileCreateDto;
import com.ignat.chernyshov.profile.domain.entities.Gender;
import com.ignat.chernyshov.profile.domain.entities.SellerProfile;
import com.ignat.chernyshov.profile.exception.exceptions.ProfileNotFoundException;
import com.ignat.chernyshov.profile.repositories.SellerProfileRepository;
import com.ignat.chernyshov.profile.repositories.specifications.SellerProfileSpecifications;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DefaultSellerProfileService implements SellerProfileService {

    private final SellerProfileRepository sellerProfileRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<SellerProfile> findByFilters(SellerProfileFilterDto filters, int page, int size) {
        Specification<SellerProfile> specification = (root, query, criteriaBuilder) -> criteriaBuilder.conjunction();

        if (filters.firstName() != null && !filters.firstName().isEmpty()) {
            specification = specification.and(SellerProfileSpecifications.hasFirstName(filters.firstName()));
        }
        if (filters.lastName() != null && !filters.lastName().isEmpty()) {
            specification = specification.and(SellerProfileSpecifications.hasLastName(filters.lastName()));
        }
        if (filters.username() != null && !filters.username().isEmpty()) {
            specification = specification.and(SellerProfileSpecifications.hasUsername(filters.username()));
        }
        if (filters.email() != null && !filters.email().isEmpty()) {
            specification = specification.and(SellerProfileSpecifications.hasEmail(filters.email()));
        }
        if (filters.phoneNumber() != null && !filters.phoneNumber().isEmpty()) {
            specification = specification.and(SellerProfileSpecifications.hasPhoneNumber(filters.phoneNumber()));
        }

        Pageable pageable = PageRequest.of(page, size);
        return sellerProfileRepository.findAll(specification, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public SellerProfile findById(Long id) {
        return sellerProfileRepository.findById(id)
                .orElseThrow(() -> new ProfileNotFoundException("Профиль не найден с id: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public SellerProfile findByUsername(String username) {
        return sellerProfileRepository.findByUsername(username)
                .orElseThrow(() -> new ProfileNotFoundException("Профиль не найден с username: " + username));
    }

    @Override
    @Transactional(readOnly = true)
    public SellerProfile findByEmail(String email) {
        return sellerProfileRepository.findByEmail(email)
                .orElseThrow(() -> new ProfileNotFoundException("Профиль не найден с email: " + email));
    }

    @Override
    @Transactional(readOnly = true)
    public SellerProfile findByPhoneNumber(String phoneNumber) {
        return sellerProfileRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new ProfileNotFoundException("Профиль не найден с phoneNumber: " + phoneNumber));
    }

    @Override
    @Transactional
    public SellerProfile createProfile(SellerProfileCreateDto dto) {
        SellerProfile sellerProfile = SellerProfile.builder()
                .id(dto.id())
                .firstName(dto.firstName())
                .lastName(dto.lastName())
                .username(dto.username())
                .email(dto.email())
                .phoneNumber(dto.phoneNumber())
                .build();

        return sellerProfileRepository.save(sellerProfile);
    }

    @Override
    @Transactional
    public SellerProfile updateProfile(Long id, SellerProfileUpdateDto dto) {
        SellerProfile sellerProfile = sellerProfileRepository.findById(id)
            .orElseThrow(() -> new ProfileNotFoundException("Профиль не найден с id: " + id));
        
        if (dto.firstName() != null) {
            sellerProfile.setFirstName(dto.firstName());
        }
        if (dto.lastName() != null) {
            sellerProfile.setLastName(dto.lastName());
        }
        if (dto.aboutMyself() != null) {
            sellerProfile.setAboutMyself(dto.aboutMyself());
        }
        if (dto.dateOfBirth() != null) {
            sellerProfile.setDateOfBirth(dto.dateOfBirth());
        }
        if (dto.gender() != null) {
            sellerProfile.setGender(dto.gender());
        }
        if (dto.avatarUrl() != null) {
            sellerProfile.setAvatarUrl(dto.avatarUrl());
        }

        return sellerProfileRepository.save(sellerProfile);
    }

    @Override
    @Transactional
    public void updateUsername(Long id, String username) {
        ensureUserExists(id);
        sellerProfileRepository.updateUsername(id, username);
    }

    @Override
    @Transactional
    public void updateEmail(Long id, String email) {
        ensureUserExists(id);
        sellerProfileRepository.updateEmail(id, email);
    }

    @Override
    @Transactional
    public void updatePhoneNumber(Long id, String phoneNumber) {
        ensureUserExists(id);
        sellerProfileRepository.updatePhoneNumber(id, phoneNumber);
    }

    @Override
    @Transactional
    public void updateFirstName(Long id, String firstName) {
        ensureUserExists(id);
        sellerProfileRepository.updateFirstName(id, firstName);
    }

    @Override
    @Transactional
    public void updateLastName(Long id, String lastName) {
        ensureUserExists(id);
        sellerProfileRepository.updateLastName(id, lastName);
    }

    @Override
    @Transactional
    public void updateAboutMyself(Long id, String aboutMyself) {
        ensureUserExists(id);
        sellerProfileRepository.updateAboutMyself(id, aboutMyself);
    }

    @Override
    @Transactional
    public void updateDateOfBirth(Long id, LocalDate dateOfBirth) {
        ensureUserExists(id);
        sellerProfileRepository.updateDateOfBirth(id, dateOfBirth);
    }

    @Override
    @Transactional
    public void updateGender(Long id, Gender gender) {
        ensureUserExists(id);
        sellerProfileRepository.updateGender(id, gender.name());
    }

    @Override
    @Transactional
    public void updateAvatarUrl(Long id, String avatarUrl) {
        ensureUserExists(id);
        sellerProfileRepository.updateAvatarUrl(id, avatarUrl);
    }

    @Override
    public void deleteProfile(Long id) {
        sellerProfileRepository.deleteById(id);
    }

    private void ensureUserExists(Long id) {
        if (!sellerProfileRepository.existsById(id)) {
            throw new ProfileNotFoundException("Профиль не найден с id: " + id);
        }
    }
}

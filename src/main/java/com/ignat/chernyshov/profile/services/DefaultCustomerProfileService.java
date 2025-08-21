package com.ignat.chernyshov.profile.services;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ignat.chernyshov.profile.domain.dto.CustomerProfileFilterDto;
import com.ignat.chernyshov.profile.domain.dto.CustomerProfileUpdateDto;
import com.ignat.chernyshov.profile.domain.dto.kafka.CustomerProfileCreateDto;
import com.ignat.chernyshov.profile.domain.entities.CustomerProfile;
import com.ignat.chernyshov.profile.domain.entities.Gender;
import com.ignat.chernyshov.profile.exception.exceptions.ProfileNotFoundException;
import com.ignat.chernyshov.profile.repositories.CustomerProfileRepository;
import com.ignat.chernyshov.profile.repositories.specifications.CustomerProfileSpecifications;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DefaultCustomerProfileService implements CustomerProfileService {

    private final CustomerProfileRepository customerProfileRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<CustomerProfile> findByFilters(CustomerProfileFilterDto filters, int page, int size) {
        Specification<CustomerProfile> specification = (root, query, criteriaBuilder) -> criteriaBuilder.conjunction();

        if (filters.firstName() != null && !filters.firstName().isEmpty()) {
            specification = specification.and(CustomerProfileSpecifications.hasFirstName(filters.firstName()));
        }
        if (filters.lastName() != null && !filters.lastName().isEmpty()) {
            specification = specification.and(CustomerProfileSpecifications.hasLastName(filters.lastName()));
        }
        if (filters.username() != null && !filters.username().isEmpty()) {
            specification = specification.and(CustomerProfileSpecifications.hasUsername(filters.username()));
        }
        if (filters.email() != null && !filters.email().isEmpty()) {
            specification = specification.and(CustomerProfileSpecifications.hasEmail(filters.email()));
        }
        if (filters.phoneNumber() != null && !filters.phoneNumber().isEmpty()) {
            specification = specification.and(CustomerProfileSpecifications.hasPhoneNumber(filters.phoneNumber()));
        }

        Pageable pageable = PageRequest.of(page, size);
        return customerProfileRepository.findAll(specification, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public CustomerProfile findById(Long id) {
        return customerProfileRepository.findById(id)
                .orElseThrow(() -> new ProfileNotFoundException("Профиль не найден с id: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public CustomerProfile findByUsername(String username) {
        return customerProfileRepository.findByUsername(username)
                .orElseThrow(() -> new ProfileNotFoundException("Профиль не найден с username: " + username));
    }

    @Override
    @Transactional(readOnly = true)
    public CustomerProfile findByEmail(String email) {
        return customerProfileRepository.findByEmail(email)
                .orElseThrow(() -> new ProfileNotFoundException("Профиль не найден с email: " + email));
    }

    @Override
    @Transactional(readOnly = true)
    public CustomerProfile findByPhoneNumber(String phoneNumber) {
        return customerProfileRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new ProfileNotFoundException("Профиль не найден с phoneNumber: " + phoneNumber));
    }

    @Override
    @Transactional
    public CustomerProfile createProfile(CustomerProfileCreateDto dto) {
        CustomerProfile customerProfile = CustomerProfile.builder()
                .id(dto.id())
                .firstName(dto.firstName())
                .lastName(dto.lastName())
                .username(dto.username())
                .email(dto.email())
                .phoneNumber(dto.phoneNumber())
                .build();

        return customerProfileRepository.save(customerProfile);
    }

    @Override
    @Transactional
    public CustomerProfile updateProfile(Long id, CustomerProfileUpdateDto dto) {
        CustomerProfile customerProfile = customerProfileRepository.findById(id)
            .orElseThrow(() -> new ProfileNotFoundException("Профиль не найден с id: " + id));
        
        if (dto.firstName() != null) {
            customerProfile.setFirstName(dto.firstName());
        }
        if (dto.lastName() != null) {
            customerProfile.setLastName(dto.lastName());
        }
        if (dto.aboutMyself() != null) {
            customerProfile.setAboutMyself(dto.aboutMyself());
        }
        if (dto.dateOfBirth() != null) {
            customerProfile.setDateOfBirth(dto.dateOfBirth());
        }
        if (dto.gender() != null) {
            customerProfile.setGender(dto.gender());
        }
        if (dto.avatarUrl() != null) {
            customerProfile.setAvatarUrl(dto.avatarUrl());
        }

        return customerProfileRepository.save(customerProfile);
    }

    @Override
    @Transactional
    public void updateUsername(Long id, String username) {
        ensureUserExists(id);
        customerProfileRepository.updateUsername(id, username);
    }

    @Override
    @Transactional
    public void updateEmail(Long id, String email) {
        ensureUserExists(id);
        customerProfileRepository.updateEmail(id, email);
    }

    @Override
    @Transactional
    public void updatePhoneNumber(Long id, String phoneNumber) {
        ensureUserExists(id);
        customerProfileRepository.updatePhoneNumber(id, phoneNumber);
    }

    @Override
    @Transactional
    public void updateFirstName(Long id, String firstName) {
        ensureUserExists(id);
        customerProfileRepository.updateFirstName(id, firstName);
    }

    @Override
    @Transactional
    public void updateLastName(Long id, String lastName) {
        ensureUserExists(id);
        customerProfileRepository.updateLastName(id, lastName);
    }

    @Override
    @Transactional
    public void updateAboutMyself(Long id, String aboutMyself) {
        ensureUserExists(id);
        customerProfileRepository.updateAboutMyself(id, aboutMyself);
    }

    @Override
    @Transactional
    public void updateDateOfBirth(Long id, LocalDate dateOfBirth) {
        ensureUserExists(id);
        customerProfileRepository.updateDateOfBirth(id, dateOfBirth);
    }

    @Override
    @Transactional
    public void updateGender(Long id, Gender gender) {
        ensureUserExists(id);
        customerProfileRepository.updateGender(id, gender.name());
    }

    @Override
    @Transactional
    public void updateAvatarUrl(Long id, String avatarUrl) {
        ensureUserExists(id);
        customerProfileRepository.updateAvatarUrl(id, avatarUrl);
    }

    @Override
    public void deleteProfile(Long id) {
        customerProfileRepository.deleteById(id);
    }

    private void ensureUserExists(Long id) {
        if (!customerProfileRepository.existsById(id)) {
            throw new ProfileNotFoundException("Профиль не найден с id: " + id);
        }
    }
}

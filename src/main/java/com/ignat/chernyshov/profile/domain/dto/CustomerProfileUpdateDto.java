package com.ignat.chernyshov.profile.domain.dto;

import java.time.LocalDate;

import com.ignat.chernyshov.profile.domain.entities.Gender;

public record CustomerProfileUpdateDto(
    String firstName,
    String lastName,
    String aboutMyself,
    LocalDate dateOfBirth,
    Gender gender,
    String avatarUrl
) {}

package com.ignat.chernyshov.profile.domain.dto;

import com.ignat.chernyshov.profile.domain.entities.Gender;

import jakarta.validation.constraints.NotNull;

public record GenderDto(
    @NotNull(message = "Поле 'gender' обязательно для заполнения")
    Gender gender
) {}

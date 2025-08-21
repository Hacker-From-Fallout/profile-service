package com.ignat.chernyshov.profile.domain.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;

public record DateOfBirthDto(
    @NotBlank(message = "Поле даты рождения не должно быть пустым")
    @PastOrPresent(message = "Дата рождения не может быть в будущем")
    LocalDate dateOfBirth
) {}

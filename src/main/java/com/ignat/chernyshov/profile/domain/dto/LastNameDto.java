package com.ignat.chernyshov.profile.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record LastNameDto(
    @NotBlank(message = "Фамилия не должно быть пустым")
    @Pattern(regexp = "^[A-Za-z]+$", message = "Last name must contain only letters")
    @Size(max = 50, message = "Фамилия не должно превышать 50 символов")
    String lastName
) {}

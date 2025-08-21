package com.ignat.chernyshov.profile.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record FirstNameDto(
    @NotBlank(message = "Имя не должно быть пустым")
    @Pattern(regexp = "^[A-Za-z]+$", message = "First name must contain only letters")
    @Size(max = 50, message = "Имя не должно превышать 50 символов")
    String firstName
) {}

package com.ignat.chernyshov.profile.domain.dto;

import org.hibernate.validator.constraints.URL;

import jakarta.validation.constraints.NotBlank;

public record AvatarUrlDto(
    @NotBlank(message = "URL аватара не должен быть пустым")
    @URL(protocol = "http", message = "Некорректный формат URL")
    String avatarUrl
) {}

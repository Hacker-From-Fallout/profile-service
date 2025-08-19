package com.ignat.chernyshov.profile.domain.dto.kafka;

public record CustomerProfileCreateDto(
    Long id,
    String firstName,
    String lastName,
    String username,
    String email,
    String phoneNumber
) {}

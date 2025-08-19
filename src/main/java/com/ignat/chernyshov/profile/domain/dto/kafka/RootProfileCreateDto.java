package com.ignat.chernyshov.profile.domain.dto.kafka;

public record RootProfileCreateDto(
    Long id,
    String username,
    String email,
    String phoneNumber,
    String firstName,
    String lastName
) {}

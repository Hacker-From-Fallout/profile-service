package com.ignat.chernyshov.profile.domain.dto;

public record CustomerProfileFilterDto(
    String firstName,
    String lastName,
    String username,
    String email,
    String phoneNumber
) {}

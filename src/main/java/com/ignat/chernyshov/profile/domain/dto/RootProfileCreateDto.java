package com.ignat.chernyshov.profile.domain.dto;

public record RootProfileCreateDto(
    String username,
    String email,
    String phoneNumber,
    String firstName,
    String lastname
) {}

package com.ignat.chernyshov.profile.domain.dto;

public record SellerProfileCreateDto(
    Long id,
    String username,
    String email,
    String phoneNumber,
    String firstName,
    String lastname
) {}

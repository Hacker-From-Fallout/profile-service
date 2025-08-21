package com.ignat.chernyshov.profile.domain.dto;

public record SellerProfileFilterDto(
    String firstName,
    String lastName,
    String username,
    String email,
    String phoneNumber
) {}

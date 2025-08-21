package com.ignat.chernyshov.profile.repositories.specifications;

import org.springframework.data.jpa.domain.Specification;

import com.ignat.chernyshov.profile.domain.entities.SellerProfile;

public class SellerProfileSpecifications {

    public static Specification<SellerProfile> hasFirstName(String firstName) {
        return (root, query, criteriaBuilder) -> {
            String searchPatten = firstName.toLowerCase() + "%";

            return criteriaBuilder.like(
                criteriaBuilder.lower(root.get("firstName")),
                searchPatten
            );
        };
    }

    public static Specification<SellerProfile> hasLastName(String lastName) {
        return (root, query, criteriaBuilder) -> {
            String searchPatten = lastName.toLowerCase() + "%";

            return criteriaBuilder.like(
                criteriaBuilder.lower(root.get("lastName")),
                searchPatten
            );
        };
    }

    public static Specification<SellerProfile> hasUsername(String username) {
        return (root, query, criteriaBuilder) -> {
            String searchPatten = username.toLowerCase() + "%";

            return criteriaBuilder.like(
                criteriaBuilder.lower(root.get("username")),
                searchPatten
            );
        };
    }

    public static Specification<SellerProfile> hasEmail(String email) {
        return (root, query, criteriaBuilder) -> {
            String searchPatten = email.toLowerCase() + "%";

            return criteriaBuilder.like(
                criteriaBuilder.lower(root.get("email")),
                searchPatten
            );
        };
    }

    public static Specification<SellerProfile> hasPhoneNumber(String phoneNumber) {
        return (root, query, criteriaBuilder) -> {
            String searchTerm = phoneNumber.trim();

            if (!searchTerm.startsWith("+")) {
                searchTerm = "+" + searchTerm;
            }

            String searchPattern = searchTerm.toLowerCase() + "%";
            return criteriaBuilder.like(
                criteriaBuilder.lower(root.get("phoneNumber")),
                searchPattern
            );
        };
    }
}

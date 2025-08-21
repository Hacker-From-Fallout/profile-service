package com.ignat.chernyshov.profile.repositories.specifications;

import org.springframework.data.jpa.domain.Specification;

import com.ignat.chernyshov.profile.domain.entities.RootProfile;

public class RootProfileSpecifications {

    public static Specification<RootProfile> hasFirstName(String firstName) {
        return (root, query, criteriaBuilder) -> {
            String searchPatten = firstName.toLowerCase() + "%";

            return criteriaBuilder.like(
                criteriaBuilder.lower(root.get("firstName")),
                searchPatten
            );
        };
    }

    public static Specification<RootProfile> hasLastName(String lastName) {
        return (root, query, criteriaBuilder) -> {
            String searchPatten = lastName.toLowerCase() + "%";

            return criteriaBuilder.like(
                criteriaBuilder.lower(root.get("lastName")),
                searchPatten
            );
        };
    }

    public static Specification<RootProfile> hasUsername(String username) {
        return (root, query, criteriaBuilder) -> {
            String searchPatten = username.toLowerCase() + "%";

            return criteriaBuilder.like(
                criteriaBuilder.lower(root.get("username")),
                searchPatten
            );
        };
    }

    public static Specification<RootProfile> hasEmail(String email) {
        return (root, query, criteriaBuilder) -> {
            String searchPatten = email.toLowerCase() + "%";

            return criteriaBuilder.like(
                criteriaBuilder.lower(root.get("email")),
                searchPatten
            );
        };
    }

    public static Specification<RootProfile> hasPhoneNumber(String phoneNumber) {
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

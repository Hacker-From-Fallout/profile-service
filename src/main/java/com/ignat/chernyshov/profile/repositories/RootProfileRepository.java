package com.ignat.chernyshov.profile.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ignat.chernyshov.profile.domain.entities.RootProfile;

public interface RootProfileRepository extends JpaRepository<RootProfile, Long>, JpaSpecificationExecutor<RootProfile> {
    List<RootProfile> findAll();
    Optional<RootProfile> findById(Long id);
    Optional<RootProfile> findByUsername(String username);
    Optional<RootProfile> findByEmail(String email);
    Optional<RootProfile> findByPhoneNumber(String phoneNumber);

    @Modifying
    @Query(value = "UPDATE root_profiles u SET first_name = :first_name WHERE u.id = :id", nativeQuery = true)
    void updateFirstName(@Param("id") Long id, @Param("first_name") String firstName);

    @Modifying
    @Query(value = "UPDATE root_profiles u SET last_name = :last_name WHERE u.id = :id", nativeQuery = true)
    void updateLastName(@Param("id") Long id, @Param("last_name") String lastName);

    @Modifying
    @Query(value = "UPDATE root_profiles p SET username = :username WHERE p.id = :id", nativeQuery = true)
    void updateUsername(@Param("id") Long id, @Param("username") String username);

    @Modifying
    @Query(value = "UPDATE root_profiles p SET email = :email WHERE p.id = :id", nativeQuery = true)
    void updateEmail(@Param("id") Long id, @Param("email") String email);

    @Modifying
    @Query(value = "UPDATE root_profiles p SET phone_number = :phone_number WHERE p.id = :id", nativeQuery = true)
    void updatePhoneNumber(@Param("id") Long id, @Param("phone_number") String phoneNumber);

    @Modifying
    @Query(value = "UPDATE root_profiles u SET about_myself = :about_myself WHERE u.id = :id", nativeQuery = true)
    void updateAboutMyself(@Param("id") Long id, @Param("about_myself") String aboutMyself);

    @Modifying
    @Query(value = "UPDATE root_profiles u SET date_of_birth = :date_of_birth WHERE u.id = :id", nativeQuery = true)
    void updateDateOfBirth(@Param("id") Long id, @Param("date_of_birth") LocalDate dateOfBirth);

    @Modifying
    @Query(value = "UPDATE root_profiles u SET gender = :gender WHERE u.id = :id", nativeQuery = true)
    void updateGender(@Param("id") Long id, @Param("gender") String gender);

    @Modifying
    @Query(value = "UPDATE root_profiles u SET avatar_url = :avatar_url WHERE u.id = :id", nativeQuery = true)
    void updateAvatarUrl(@Param("id") Long id, @Param("avatar_url") String avatarUrl);
    boolean existsById(Long id);

    void deleteById(Long id);
}

package com.ignat.chernyshov.profile.controllers;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ignat.chernyshov.profile.domain.dto.AboutMyselfDto;
import com.ignat.chernyshov.profile.domain.dto.AvatarUrlDto;
import com.ignat.chernyshov.profile.domain.dto.DateOfBirthDto;
import com.ignat.chernyshov.profile.domain.dto.FirstNameDto;
import com.ignat.chernyshov.profile.domain.dto.GenderDto;
import com.ignat.chernyshov.profile.domain.dto.LastNameDto;
import com.ignat.chernyshov.profile.domain.dto.RootProfileFilterDto;
import com.ignat.chernyshov.profile.domain.entities.RootProfile;
import com.ignat.chernyshov.profile.services.RootProfileService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/root-profiles")
public class RootProfileController {

    private final RootProfileService rootProfileService;

    @GetMapping
    public ResponseEntity<Page<RootProfile>> getAll(
        @ModelAttribute RootProfileFilterDto filters,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size
    ) {
        Page<RootProfile> pageResult = rootProfileService.findByFilters(filters, page, size);
        return ResponseEntity.status(HttpStatus.OK).body(pageResult);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        RootProfile rootProfile = rootProfileService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(rootProfile);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<?> getByUsername(@PathVariable String username) {
        RootProfile rootProfile = rootProfileService.findByUsername(username);
        return ResponseEntity.status(HttpStatus.OK).body(rootProfile); 
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<?> getByEmail(@PathVariable String email) {
        RootProfile rootProfile = rootProfileService.findByEmail(email);
        return ResponseEntity.status(HttpStatus.OK).body(rootProfile); 
    }

    @GetMapping("/phone-number/{phone-number}")
    public ResponseEntity<?> getByPhoneNumber(@PathVariable String phoneNumber) {
        RootProfile rootProfile = rootProfileService.findByPhoneNumber(phoneNumber);
        return ResponseEntity.status(HttpStatus.OK).body(rootProfile); 
    }

    @PatchMapping("/{id}/first-name")
    public ResponseEntity<?> updateFirstName(@PathVariable Long id, @Valid @RequestBody FirstNameDto dto) {
        rootProfileService.updateFirstName(id, dto.firstName());
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/last-name")
    public ResponseEntity<?> updateLastName(@PathVariable Long id, @Valid @RequestBody LastNameDto dto) {
        rootProfileService.updateLastName(id, dto.lastName());
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/about-myself")
    public ResponseEntity<?> updateAboutMyself(@PathVariable Long id, @Valid @RequestBody AboutMyselfDto dto) {
        rootProfileService.updateAboutMyself(id, dto.aboutMyself());
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/date-of-birth")
    public ResponseEntity<?> updateDateOfBirth(@PathVariable Long id, @Valid @RequestBody DateOfBirthDto dto) {
        rootProfileService.updateDateOfBirth(id, dto.dateOfBirth());
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/gender")
    public ResponseEntity<?> updateGender(@PathVariable Long id, @Valid @RequestBody GenderDto dto) {
        rootProfileService.updateGender(id, dto.gender());
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/avatar-url")
    public ResponseEntity<?> updateAvatarUrl(@PathVariable Long id, @Valid @RequestBody AvatarUrlDto dto) {
        rootProfileService.updateAvatarUrl(id, dto.avatarUrl());
        return ResponseEntity.noContent().build();
    }
}

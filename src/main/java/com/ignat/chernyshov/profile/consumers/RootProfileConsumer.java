package com.ignat.chernyshov.profile.consumers;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.ignat.chernyshov.profile.domain.dto.kafka.RootProfileCreateDto;
import com.ignat.chernyshov.profile.domain.dto.kafka.UserEmailUpdateDto;
import com.ignat.chernyshov.profile.domain.dto.kafka.UserPhoneNumberUpdateDto;
import com.ignat.chernyshov.profile.domain.dto.kafka.UserUsernameUpdateDto;
import com.ignat.chernyshov.profile.services.RootProfileService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RootProfileConsumer {

    private final RootProfileService rootProfileService;
    
    @KafkaListener(topics = "${spring.kafka.topics.root.create-profile}", groupId = "root-profile-id", containerFactory = "createRootProfileKafkaListenerContainerFactory")
    public void handleCreateProfile(RootProfileCreateDto dto) {
        rootProfileService.createProfile(dto);
    }

    @KafkaListener(topics = "${spring.kafka.topics.root.update-username}", groupId = "root-profile-id", containerFactory = "updateRootUsernameKafkaListenerContainerFactory")
    public void handleUpdateUsername(UserUsernameUpdateDto dto) {
        rootProfileService.updateUsername(dto.id(), dto.username());
    }

    @KafkaListener(topics = "${spring.kafka.topics.root.update-email}", groupId = "root-profile-id", containerFactory = "updateRootEmailKafkaListenerContainerFactory")
    public void handleUpdateEmail(UserEmailUpdateDto dto) {
        rootProfileService.updateEmail(dto.id(), dto.email());
    }

    @KafkaListener(topics = "${spring.kafka.topics.root.update-phone-number}", groupId = "root-profile-id", containerFactory = "updateRootPhoneNumberKafkaListenerContainerFactory")
    public void handleUpdatePhoneNumber(UserPhoneNumberUpdateDto dto) {
        rootProfileService.updatePhoneNumber(dto.id(), dto.phoneNumber());
    }

    @KafkaListener(topics = "${spring.kafka.topics.root.delete-profile}", groupId = "root-profile-id", containerFactory = "longRootKafkaListenerContainerFactory")
    public void handleDeleteProfile(Long id) {
        rootProfileService.deleteProfile(id);
    }
}

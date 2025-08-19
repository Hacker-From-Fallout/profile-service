package com.ignat.chernyshov.profile.consumers;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.ignat.chernyshov.profile.domain.dto.kafka.SellerProfileCreateDto;
import com.ignat.chernyshov.profile.domain.dto.kafka.UserEmailUpdateDto;
import com.ignat.chernyshov.profile.domain.dto.kafka.UserPhoneNumberUpdateDto;
import com.ignat.chernyshov.profile.domain.dto.kafka.UserUsernameUpdateDto;
import com.ignat.chernyshov.profile.services.SellerProfileService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SellerProfileConsumer {

    private final SellerProfileService sellerProfileService;
    
    @KafkaListener(topics = "${spring.kafka.topics.seller.create-profile}", groupId = "seller-profile-id", containerFactory = "createSellerProfileKafkaListenerContainerFactory")
    public void handleCreateProfile(SellerProfileCreateDto dto) {
        sellerProfileService.createProfile(dto);
    }

    @KafkaListener(topics = "${spring.kafka.topics.seller.update-username}", groupId = "seller-profile-id", containerFactory = "updateSellerUsernameKafkaListenerContainerFactory")
    public void handleUpdateUsername(UserUsernameUpdateDto dto) {
        sellerProfileService.updateUsername(dto.id(), dto.username());
    }

    @KafkaListener(topics = "${spring.kafka.topics.seller.update-email}", groupId = "seller-profile-id", containerFactory = "updateSellerEmailKafkaListenerContainerFactory")
    public void handleUpdateEmail(UserEmailUpdateDto dto) {
        sellerProfileService.updateEmail(dto.id(), dto.email());
    }

    @KafkaListener(topics = "${spring.kafka.topics.seller.update-phone-number}", groupId = "seller-profile-id", containerFactory = "updateSellerPhoneNumberKafkaListenerContainerFactory")
    public void handleUpdatePhoneNumber(UserPhoneNumberUpdateDto dto) {
        sellerProfileService.updatePhoneNumber(dto.id(), dto.phoneNumber());
    }

    @KafkaListener(topics = "${spring.kafka.topics.seller.delete-profile}", groupId = "seller-profile-id", containerFactory = "longSellerKafkaListenerContainerFactory")
    public void handleDeleteProfile(Long id) {
        sellerProfileService.deleteProfile(id);
    }
}

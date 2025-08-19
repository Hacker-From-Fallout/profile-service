package com.ignat.chernyshov.profile.consumers;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.ignat.chernyshov.profile.domain.dto.kafka.CustomerProfileCreateDto;
import com.ignat.chernyshov.profile.domain.dto.kafka.UserEmailUpdateDto;
import com.ignat.chernyshov.profile.domain.dto.kafka.UserPhoneNumberUpdateDto;
import com.ignat.chernyshov.profile.domain.dto.kafka.UserUsernameUpdateDto;
import com.ignat.chernyshov.profile.services.CustomerProfileService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomerProfileConsumer {

    private final CustomerProfileService customerProfileService;
    
    @KafkaListener(topics = "${spring.kafka.topics.customer.create-profile}", groupId = "customer-profile-id", containerFactory = "createCustomerProfileKafkaListenerContainerFactory")
    public void handleCreateProfile(CustomerProfileCreateDto dto) {
        customerProfileService.createProfile(dto);
    }

    @KafkaListener(topics = "${spring.kafka.topics.customer.update-username}", groupId = "customer-profile-id", containerFactory = "updateCustomerUsernameKafkaListenerContainerFactory")
    public void handleUpdateUsername(UserUsernameUpdateDto dto) {
        customerProfileService.updateUsername(dto.id(), dto.username());
    }

    @KafkaListener(topics = "${spring.kafka.topics.customer.update-email}", groupId = "customer-profile-id", containerFactory = "updateCustomerEmailKafkaListenerContainerFactory")
    public void handleUpdateEmail(UserEmailUpdateDto dto) {
        customerProfileService.updateEmail(dto.id(), dto.email());
    }

    @KafkaListener(topics = "${spring.kafka.topics.customer.update-phone-number}", groupId = "customer-profile-id", containerFactory = "updateCustomerPhoneNumberKafkaListenerContainerFactory")
    public void handleUpdatePhoneNumber(UserPhoneNumberUpdateDto dto) {
        customerProfileService.updatePhoneNumber(dto.id(), dto.phoneNumber());
    }

    @KafkaListener(topics = "${spring.kafka.topics.customer.delete-profile}", groupId = "customer-profile-id", containerFactory = "longCustomerKafkaListenerContainerFactory")
    public void handleDeleteProfile(Long id) {
        customerProfileService.deleteProfile(id);
    }
}
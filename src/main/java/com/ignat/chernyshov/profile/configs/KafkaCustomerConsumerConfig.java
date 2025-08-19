package com.ignat.chernyshov.profile.configs;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;

import com.ignat.chernyshov.profile.domain.dto.kafka.CustomerProfileCreateDto;
import com.ignat.chernyshov.profile.domain.dto.kafka.UserEmailUpdateDto;
import com.ignat.chernyshov.profile.domain.dto.kafka.UserPhoneNumberUpdateDto;
import com.ignat.chernyshov.profile.domain.dto.kafka.UserUsernameUpdateDto;

import lombok.RequiredArgsConstructor;

@EnableKafka
@Configuration
@RequiredArgsConstructor
public class KafkaCustomerConsumerConfig {

    private static final String TRUSTED_PACKAGES = "com.ignat.chernyshov.profile.domain.dto.kafka";

    private final KafkaProperties kafkaProperties;

    public ConsumerFactory<String, CustomerProfileCreateDto> consumerCustomerCreateProfileFactory() {
        return KafkaConsumerFactoryBuilder.buildConsumerFactory(
                kafkaProperties.getProperties(),
                "customer-profile-id",
                CustomerProfileCreateDto.class,
                TRUSTED_PACKAGES
        );
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, CustomerProfileCreateDto> createCustomerProfileKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, CustomerProfileCreateDto> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerCustomerCreateProfileFactory());
        return factory;
    }

    public ConsumerFactory<String, UserUsernameUpdateDto> consumerCustomerUpdateUsernameFactory() {
        return KafkaConsumerFactoryBuilder.buildConsumerFactory(
                kafkaProperties.getProperties(),
                "customer-profile-id",
                UserUsernameUpdateDto.class,
                TRUSTED_PACKAGES
        );
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, UserUsernameUpdateDto> updateCustomerUsernameKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, UserUsernameUpdateDto> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerCustomerUpdateUsernameFactory());
        return factory;
    }


    public ConsumerFactory<String, UserEmailUpdateDto> consumerCustomerUpdateEmailFactory() {
        return KafkaConsumerFactoryBuilder.buildConsumerFactory(
                kafkaProperties.getProperties(),
                "customer-profile-id",
                UserEmailUpdateDto.class,
                TRUSTED_PACKAGES
        );
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, UserEmailUpdateDto> updateCustomerEmailKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, UserEmailUpdateDto> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerCustomerUpdateEmailFactory());
        return factory;
    }

    public ConsumerFactory<String, UserPhoneNumberUpdateDto> consumerCustomerUpdatePhoneNumberFactory() {
        return KafkaConsumerFactoryBuilder.buildConsumerFactory(
                kafkaProperties.getProperties(),
                "customer-profile-id",
                UserPhoneNumberUpdateDto.class,
                TRUSTED_PACKAGES
        );
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, UserPhoneNumberUpdateDto> updateCustomerPhoneNumberKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, UserPhoneNumberUpdateDto> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerCustomerUpdatePhoneNumberFactory());
        return factory;
    }

    public ConsumerFactory<String, Long> consumerCustomerLongFactory() {
        Map<String, Object> configProps = new HashMap<>();
        
        configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
        configProps.put(ConsumerConfig.REQUEST_TIMEOUT_MS_CONFIG, 30000);
        configProps.put(ConsumerConfig.FETCH_MAX_WAIT_MS_CONFIG, 500);
        configProps.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, 10000);
        configProps.put(ConsumerConfig.GROUP_ID_CONFIG, "customer-profile-id");
        configProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        configProps.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
        configProps.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 50);
        configProps.put(ConsumerConfig.FETCH_MIN_BYTES_CONFIG, 1);
        configProps.put(ConsumerConfig.HEARTBEAT_INTERVAL_MS_CONFIG, 3000);
        configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
        configProps.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, LongDeserializer.class);

        return new DefaultKafkaConsumerFactory<>(configProps);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Long> longCustomerKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Long> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerCustomerLongFactory());
        return factory;
    }
}

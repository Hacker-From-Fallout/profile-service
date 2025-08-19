package com.ignat.chernyshov.profile.configs;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.mapping.DefaultJackson2JavaTypeMapper;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

public class KafkaConsumerFactoryBuilder {

    public static <T> ConsumerFactory<String, T> buildConsumerFactory(
        Map<String, Object> kafkaProperties,
        String groupId,
        Class<T> valueType,
        String trustedPackage
    ) {
        Map<String, Object> configProps = new HashMap<>();
        
        configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.get(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG));
        configProps.put(ConsumerConfig.REQUEST_TIMEOUT_MS_CONFIG, 30000);
        configProps.put(ConsumerConfig.FETCH_MAX_WAIT_MS_CONFIG, 500);
        configProps.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, 10000);
        configProps.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        configProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        configProps.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
        configProps.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 50);
        configProps.put(ConsumerConfig.FETCH_MIN_BYTES_CONFIG, 1);
        configProps.put(ConsumerConfig.HEARTBEAT_INTERVAL_MS_CONFIG, 3000);
        configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
        configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

        JsonDeserializer<T> deserializer = new JsonDeserializer<>(valueType);
        deserializer.setTypeMapper(new DefaultJackson2JavaTypeMapper() {{
            addTrustedPackages(trustedPackage);
        }});
        deserializer.setRemoveTypeHeaders(true);

        ErrorHandlingDeserializer<T> errorDeserializer = new ErrorHandlingDeserializer<>(deserializer);

        return new DefaultKafkaConsumerFactory<>(configProps, new StringDeserializer(), errorDeserializer);
    }
}

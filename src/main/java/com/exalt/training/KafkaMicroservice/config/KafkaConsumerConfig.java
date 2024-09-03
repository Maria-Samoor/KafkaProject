package com.exalt.training.KafkaMicroservice.config;

import com.exalt.training.KafkaMicroservice.model.Message;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

/**
 * Configuration class for Kafka consumer.
 */
@Configuration
public class KafkaConsumerConfig {
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers; // Kafka bootstrap servers address

    /**
     * Provides the configuration for the Kafka consumer.
     *
     * @return A map containing the consumer configuration properties.
     */
    public Map<String,Object> consumerConfig(){
        Map<String, Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class); // Changed to JsonDeserializer
        config.put(JsonDeserializer.TRUSTED_PACKAGES, "*"); // Needed for JsonDeserializer to work correctly
        return config;
    }

    /**
     * Creates a ConsumerFactory for consuming messages from Kafka.
     *
     * @return The created ConsumerFactory.
     */
    @Bean
    public ConsumerFactory<String, Message> consumerFactory(){
        return new DefaultKafkaConsumerFactory<>(consumerConfig(), new StringDeserializer(), new JsonDeserializer<>(Message.class));
    }

    /**
     * Creates a KafkaListenerContainerFactory for managing Kafka listener containers.
     *
     * @param consumerFactory The ConsumerFactory to be used by the KafkaListenerContainerFactory.
     * @return The created KafkaListenerContainerFactory.
     */
    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, Message>> factory(ConsumerFactory<String, Message> consumerFactory){
        ConcurrentKafkaListenerContainerFactory<String, Message> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        return factory;
    }
}

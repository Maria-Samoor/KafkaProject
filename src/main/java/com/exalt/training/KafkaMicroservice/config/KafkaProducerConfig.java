package com.exalt.training.KafkaMicroservice.config;

import com.exalt.training.KafkaMicroservice.model.Message;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

/**
 * Configuration class for Kafka producer.
 */
@Configuration
public class KafkaProducerConfig {
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers; // Kafka bootstrap servers address

    /**
     * Provides the configuration for the Kafka producer.
     *
     * @return A map containing the producer configuration properties.
     */
    public Map<String,Object> producerConfig(){
        Map<String, Object> config = new HashMap<>();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class); // Changed to JsonSerializer
        return config;
    }

    /**
     * Creates a ProducerFactory for producing messages to Kafka.
     *
     * @return The created ProducerFactory.
     */
    @Bean
    public ProducerFactory<String, Message> producerFactory(){
        return new DefaultKafkaProducerFactory<>(producerConfig());
    }

    /**
     * Creates a KafkaTemplate for sending messages to Kafka.
     *
     * @param producerFactory The ProducerFactory to be used by the KafkaTemplate.
     * @return The created KafkaTemplate.
     */
    @Bean
    public KafkaTemplate<String, Message> kafkaTemplate(ProducerFactory<String, Message> producerFactory){
        return new KafkaTemplate<>(producerFactory);
    }

}

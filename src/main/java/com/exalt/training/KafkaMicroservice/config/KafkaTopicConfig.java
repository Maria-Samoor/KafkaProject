package com.exalt.training.KafkaMicroservice.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

/**
 * Configuration class for Kafka topics.
 */
@Configuration
public class KafkaTopicConfig {

    /**
     * Creates a new Kafka topic named "messaging-topic".
     *
     * @return The created Kafka topic.
     */
    @Bean
    public NewTopic taskTopic() {
        return TopicBuilder.name("messaging-topic")
                .build();
    }
}

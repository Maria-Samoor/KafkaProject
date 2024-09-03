package com.exalt.training.KafkaMicroservice.service;

import com.exalt.training.KafkaMicroservice.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * Service class responsible for listening to Kafka topics.
 */
@Component
@Slf4j
public class KafkaListeners {

    /**
     * Method that listens to the "messaging-topic" Kafka topic.
     *
     * @param message The message received from the Kafka topic.
     */
    @KafkaListener(topics = {"messaging-topic"}, groupId = "1st-group")
    void listener(Message message){
        log.info(String.format("Received: %s" , message));
    }
}

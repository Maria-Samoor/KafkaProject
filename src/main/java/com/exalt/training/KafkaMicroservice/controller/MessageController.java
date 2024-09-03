package com.exalt.training.KafkaMicroservice.controller;

import com.exalt.training.KafkaMicroservice.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller responsible for handling HTTP requests related to Kafka messaging.
 */
@Slf4j
@RequestMapping("/exalt/training/kafka")
@RestController
public class MessageController {
    private KafkaTemplate<String, Message> kafkaTemplate; // Kafka template for sending messages

    /**
     * Constructor for MessageController.
     *
     * @param kafkaTemplate The Kafka template to be used for sending messages.
     */
    public MessageController(KafkaTemplate<String, Message> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    /**
     * Endpoint to produce a message to the Kafka topic.
     *
     * @param message The message to be sent.
     * @return Response entity indicating whether the operation was successful.
     */
    @PostMapping("/message")
    public ResponseEntity<Boolean> produce(@RequestBody Message message) {
        try {
            kafkaTemplate.send("messaging-topic", message);
            log.info("message " + message + " is received");
            return ResponseEntity.ok(Boolean.TRUE);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return ResponseEntity.ok(Boolean.FALSE);
        }
    }
}


package com.exalt.training.KafkaMicroservice.controller;

import com.exalt.training.KafkaMicroservice.model.Message;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

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
    public ResponseEntity<?> produce(@Valid @RequestBody Message message, BindingResult bindingResult) {
        Map<String, Object> response = new HashMap<>();
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getFieldError().getDefaultMessage());
        }
        try {
            kafkaTemplate.send("messaging-topic", message);
            log.info("message " + message + " is received");
            response.put("code", HttpStatus.OK.value());
            response.put("status", "Success");
            response.put("message", "Message sent successfully");
            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            log.error("Failed to send message: {}", ex.getMessage());
            response.put("status", "Failure");
            response.put("message", "Failed to send message: " + ex.getMessage());
            response.put("code", HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.badRequest().body(response);
        }
    }
}


package com.exalt.training.KafkaMicroservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A simple model class representing a message.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    private String message; // The message content
}
package com.exalt.training.KafkaMicroservice.model;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
/**
 * A simple model class representing a message.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    @NotNull(message = "ID cannot be null")
    @NotEmpty(message = "ID cannot be empty")
    @Size(min = 1, max = 50, message = "ID must be between 1 and 50 characters")
    private String id; // The message id
    @NotNull(message = "Message content cannot be null")
    @NotEmpty(message = "Message content cannot be empty")
    @Size(min = 1, max = 500, message = "Message content must be between 1 and 500 characters")
    private String message; // The message content
}
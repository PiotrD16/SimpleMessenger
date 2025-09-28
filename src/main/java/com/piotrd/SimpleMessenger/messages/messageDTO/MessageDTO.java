package com.piotrd.SimpleMessenger.messages.messageDTO;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MessageDTO {
    private String contentMessage;
    private LocalDateTime timestampMessage;
    private String roomName;
    private Long senderId;
    private Long receiverId;
}
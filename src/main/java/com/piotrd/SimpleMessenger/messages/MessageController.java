package com.piotrd.SimpleMessenger.messages;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.piotrd.SimpleMessenger.messages.messageDTO.MessageDTO;
import com.piotrd.SimpleMessenger.users.User;
import com.piotrd.SimpleMessenger.users.UserRepo;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MessageController {
    private final SimpMessagingTemplate messagingTemplate;
    private final MessageService messageService;
    private final UserRepo userRepo;

    @MessageMapping("/send")
    public void sendPrivateMessage(@Payload MessageDTO message){
        messageService.saveMessage(message); // Save message to the database

        User receiver = userRepo.findById(message.getReceiverId()).orElseThrow(() -> new RuntimeException("Receiver not found"));

        messagingTemplate.convertAndSendToUser(receiver.getEmailUser(), "/queue/messages", message); // Send to specific user
    }

    @MessageMapping("/chat")
    public void sendMessageToRoom(@Payload MessageDTO message){
        messageService.saveMessage(message); // Save message to the database
        messagingTemplate.convertAndSend("/topic/" + message.getRoomName(), message); // Send to specific room
    }
}
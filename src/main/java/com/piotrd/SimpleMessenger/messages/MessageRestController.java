package com.piotrd.SimpleMessenger.messages;

import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/messages")
public class MessageRestController {
    private final MessageService messageService;

    @GetMapping("/room/{roomName}")
    public ResponseEntity<List<Message>> getMessagesByRoomName(@PathVariable String roomName){
        List<Message> messages = messageService.getMessagesByRoomName(roomName);
        if(messages.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(messages);
    }

    @GetMapping("/receiver/{receiverId}")
    public ResponseEntity<List<Message>> getMessagesByReceiverId(@PathVariable Long receiverId){
        List<Message> messages = messageService.getMessagesByReceiverId(receiverId);
        if(messages.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(messages);
        
    }

    @GetMapping("/sender/{senderId}")
    public ResponseEntity<List<Message>> getMessagesBySenderId(@PathVariable Long senderId){
        List<Message> messages = messageService.getMessagesBySenderId(senderId);
        if(messages.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(messages);
        
    }

    @GetMapping("/conversation/{senderId}/{receiverId}")
    public ResponseEntity<List<Message>> getMessagesBySenderAndReceiverId(@PathVariable Long senderId, @PathVariable Long receiverId){
        List<Message> messages = messageService.getMessagesBySenderAndReceiverId(senderId, receiverId);
        if(messages.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(messages);
    }


}
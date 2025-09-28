package com.piotrd.SimpleMessenger.messages;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.piotrd.SimpleMessenger.messages.messageDTO.MessageDTO;
import com.piotrd.SimpleMessenger.users.UserRepo;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class MessageService {
    private final MessageRepo messageRepo;
    private final UserRepo userRepo;

    public Message saveMessage(MessageDTO message){
        Message newMessage = new Message();
        newMessage.setContentMessage(message.getContentMessage());
        newMessage.setRoomName(message.getRoomName());
        newMessage.setTimestampMessage(message.getTimestampMessage()!= null ? message.getTimestampMessage() : LocalDateTime.now());
        newMessage.setSender(userRepo.findById(message.getSenderId()).orElseThrow(() -> new RuntimeException("User not found")));
        if(message.getReceiverId() != null){
            newMessage.setReceiver(userRepo.findById(message.getReceiverId()).orElseThrow(() -> new RuntimeException("Receiver not found")));
        } 
        return messageRepo.save(newMessage);
    }

    public List<Message> getMessagesByRoomName(String roomName){
        return messageRepo.findByRoomName(roomName);
    }

    public List<Message> getMessagesByReceiverId(Long receiverId){
        return messageRepo.findByReceiver_IdUser(receiverId);
    }

    public List<Message> getMessagesBySenderId(Long senderId){
        return messageRepo.findBySender_IdUser(senderId);
    }

    public List<Message> getMessagesBySenderAndReceiverId(Long senderId, Long receiverId){
        return messageRepo.findBySender_IdUserAndReceiver_IdUser(senderId, receiverId);
    }
}
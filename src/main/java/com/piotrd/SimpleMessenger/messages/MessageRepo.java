package com.piotrd.SimpleMessenger.messages;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MessageRepo extends JpaRepository<Message, Long> {
    List<Message> findByRoomName(String roomName);
    List<Message> findByReceiver_IdUser(Long receiverId);
    List<Message> findBySender_IdUser(Long senderId);
    List<Message> findBySender_IdUserAndReceiver_IdUser(Long senderId, Long receiverId);
}
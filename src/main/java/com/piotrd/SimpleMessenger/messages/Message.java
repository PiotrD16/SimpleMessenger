package com.piotrd.SimpleMessenger.messages;

import java.time.LocalDateTime;

import com.piotrd.SimpleMessenger.users.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "messages")
@AllArgsConstructor
@NoArgsConstructor
@Data

public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_message", nullable = false)
    private Long idMessage;

    @Column(name = "content_message", length = 500)
    private String contentMessage;

    @Column(name = "timestamp_message", updatable = false)
    private LocalDateTime timestampMessage;

    @Column(name = "room_name", length = 200, nullable = true)
    private String roomName;

    @ManyToOne
    @JoinColumn(name = "idUser", nullable = false)
    private User sender;

    @ManyToOne
    @JoinColumn(name = "idReceiver", nullable = true)
    private User receiver;

    @PrePersist
    public void setTimestamp(){
        if(this.timestampMessage == null)
            this.timestampMessage = LocalDateTime.now();
    }
}
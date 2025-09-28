package com.piotrd.SimpleMessenger.users;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.piotrd.SimpleMessenger.enums.Roles;
import com.piotrd.SimpleMessenger.messages.Message;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Data

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user", nullable = false)
    private Long idUser;

    @Column(name = "email_user", nullable = false, unique = true, length = 50)
    @Email
    private String emailUser;

    @Column(name = "password_user", nullable = false, length = 255)
    private String passwordUser;

    @Column(name = "name_user", nullable = false, unique = true, length = 50)
    private String nameUser;

    @Enumerated(EnumType.STRING)
    @Column(name = "role_user", nullable = false)
    private Roles roleUser;

    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("sender")
    private List<Message> messagesSent = new ArrayList<>();

    @OneToMany(mappedBy = "receiver", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("receiver")
    private List<Message> messagesReceived = new ArrayList<>();
}
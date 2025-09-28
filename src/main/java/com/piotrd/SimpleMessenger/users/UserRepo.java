package com.piotrd.SimpleMessenger.users;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByEmailUser(String emailUser);
    List<User> findByNameUserContaining(String nameUser);
}
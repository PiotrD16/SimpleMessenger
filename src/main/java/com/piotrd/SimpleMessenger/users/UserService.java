package com.piotrd.SimpleMessenger.users;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.piotrd.SimpleMessenger.enums.Roles;
import com.piotrd.SimpleMessenger.users.userDTO.UserDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepo;

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public Optional<User> findById(Long id) {
        return userRepo.findById(id);
    }
    
    public List<User> findByNameUserContaining(String nameUser) {
        return userRepo.findByNameUserContaining(nameUser);
    }

    public Optional<User> findByEmailUser(String emailUser) {
        return userRepo.findByEmailUser(emailUser);
    }

    public User saveUser(UserDTO user) {
        Optional<User> existingUser = userRepo.findByEmailUser(user.getEmailUser());
        if (existingUser.isPresent()) {
            throw new RuntimeException("User already exists");
        }
        User newUser = new User();
        newUser.setEmailUser(user.getEmailUser());
        newUser.setNameUser(user.getNameUser());
        newUser.setPasswordUser(user.getPasswordUser());
        newUser.setRoleUser(Roles.USER);

        return userRepo.save(newUser);
    }

    public User updateUser(Long id, UserDTO user) {
        Optional<User> existingUser = userRepo.findById(id);
        if (existingUser.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        User updatedUser = existingUser.get();
        updatedUser.setEmailUser(user.getEmailUser());
        updatedUser.setNameUser(user.getNameUser());
        updatedUser.setPasswordUser(user.getPasswordUser());

        return userRepo.save(updatedUser);
    }

    public User updateUserRole(Long id, Roles role) {
        Optional<User> existingUser = userRepo.findById(id);
        if (existingUser.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        User updatedUser = existingUser.get();
        updatedUser.setRoleUser(role);

        return userRepo.save(updatedUser);
    }

    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    } 
}
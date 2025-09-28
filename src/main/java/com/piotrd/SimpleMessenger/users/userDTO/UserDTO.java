package com.piotrd.SimpleMessenger.users.userDTO;

import com.piotrd.SimpleMessenger.enums.Roles;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {
    @NotBlank @Size(min=6,max=50) private String nameUser;
    @NotBlank @Size(min=6, max=50) @Email private String emailUser;
    @NotBlank @Size(min=6, max=255) private String passwordUser;
    private Roles roleUser;
}
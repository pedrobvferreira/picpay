package com.challenge.picpay.dto;

import com.challenge.picpay.domain.enums.UserRole;
import lombok.Data;

@Data
public class RegisterDTO {
    private String login;
    private String password;
    private UserRole role;
}

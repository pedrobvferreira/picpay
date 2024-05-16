package com.challenge.picpay.dto;

import com.challenge.picpay.enums.UserTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequestDTO {
    private String name;
    private String email;
    private String password;
    private UserTypeEnum type;
}

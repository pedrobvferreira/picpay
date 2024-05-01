package com.challenge.picpay.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO{
    private String name;
    private String cpf;
    private BigDecimal balance;
    private String email;
    private String pass;
    private String userType;
}

package com.challenge.picpay.dto;

import com.challenge.picpay.enums.UserRole;
import com.challenge.picpay.enums.UserTypeEnum;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO{

    @NotNull
    @NotBlank
    private String name;
    private String cpf;
    @NotNull
    @NotBlank
    private String email;
    @NotNull
    @NotBlank
    private String pass;
    @NotNull
    private BigDecimal balance;
    private UserTypeEnum type;
    private UserRole role;

}

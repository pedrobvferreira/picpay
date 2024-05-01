package com.challenge.picpay.dto;

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
    private BigDecimal balance;
    @NotNull
    @NotBlank
    private String email;
    @NotNull
    @NotBlank
    private String pass;
    @NotNull
    @NotBlank
    private String type;
}

package com.challenge.picpay.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorizationDTO {
    private String message;

    public boolean isAuthorized() {
        return message.equals("Autorizado");
    }
}

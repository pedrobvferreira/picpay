package com.challenge.picpay.service;

import com.challenge.picpay.domain.User;
import com.challenge.picpay.dto.AuthorizationDTO;
import com.challenge.picpay.exception.UnauthorizedException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Objects;

@AllArgsConstructor
@Service
public class AuthorizationService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${app.authorizationApi}")
    private String authApiUrl;

    public boolean authorizeTransaction(User sender, BigDecimal value){
        ResponseEntity<AuthorizationDTO> authorizationResponse =
                restTemplate.getForEntity(this.authApiUrl, AuthorizationDTO.class);

        if(authorizationResponse.getStatusCode() == HttpStatus.OK){
            return Objects.requireNonNull(authorizationResponse.getBody()).isAuthorized();
        } else{
            throw new UnauthorizedException("Unauthorized!");
        }
    }
}

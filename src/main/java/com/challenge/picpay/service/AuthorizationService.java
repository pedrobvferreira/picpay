package com.challenge.picpay.service;

import com.challenge.picpay.domain.User;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Objects;

@Service
public class AuthorizationService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${app.authorizationApi}")
    private String authApiUrl;

    public boolean authorizeTransaction(User sender, BigDecimal value){
        ResponseEntity<Map> authorizationResponse = restTemplate.getForEntity(this.authApiUrl, Map.class);

        if(authorizationResponse.getStatusCode() == HttpStatus.OK){
            String message = (String) Objects.requireNonNull(authorizationResponse.getBody()).get("message");
            return "Autorizado".equalsIgnoreCase(message);
        } else return false;
    }
}

package com.challenge.picpay.controller;

import com.challenge.picpay.domain.User;
import com.challenge.picpay.enums.UserRole;
import com.challenge.picpay.dto.AuthenticationDTO;
import com.challenge.picpay.dto.LoginResponseDTO;
import com.challenge.picpay.dto.RegisterRequestDTO;
import com.challenge.picpay.dto.UserDTO;
import com.challenge.picpay.service.TokenService;
import com.challenge.picpay.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AuthenticationDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.getEmail(), data.getPassword());
        var auth = authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((User) auth.getPrincipal());

        return new ResponseEntity<>(new LoginResponseDTO(token), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody @Valid RegisterRequestDTO data){
        if(userService.findByEmail(data.getEmail()) == null) {
            return ResponseEntity.badRequest().build();
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.getPassword());
        UserDTO newUserDto = new UserDTO(
                data.getName(),
                null,
                data.getEmail(),
                encryptedPassword,
                new BigDecimal("0.00"),
                data.getType(),
                UserRole.USER);

        userService.createUser(newUserDto);

        return new ResponseEntity<>(newUserDto, HttpStatus.OK);
    }
}

package com.challenge.picpay.controller;

import com.challenge.picpay.dto.UserDTO;
import com.challenge.picpay.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/users/")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("private/createUser")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO user){
        UserDTO newUser = userService.createUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @GetMapping("public/getUser/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable("id") Long id){
        UserDTO newUser = userService.getUserById(id);
        return new ResponseEntity<>(newUser, HttpStatus.OK);
    }

    @GetMapping("public/getUsers")
    public ResponseEntity<List<UserDTO>> getUsers(){
        List<UserDTO> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}

package com.challenge.picpay.service;

import com.challenge.picpay.domain.User;
import com.challenge.picpay.domain.enums.UserTypeEnum;
import com.challenge.picpay.dto.UserDTO;
import com.challenge.picpay.exception.InsufficientBalanceException;
import com.challenge.picpay.exception.NotFoundException;
import com.challenge.picpay.exception.UnauthorizedException;
import com.challenge.picpay.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;

    public void validateTransaction(User sender, BigDecimal amount) throws Exception {
        if (sender.getType().equals(UserTypeEnum.MERCHANT.getValue())) {
            throw new UnauthorizedException("O user Lojista não está autorizado a realizar transação");
        }

        if(sender.getBalance().compareTo(amount) < 0){
            throw new InsufficientBalanceException("Saldo insuficiente");
        }
    }

    public UserDTO createUser(UserDTO userDTO){
        User newUser = new User();
        saveUser(newUser);
        modelMapper.map(userDTO, newUser);
        return userDTO;
    }

    public void saveUser(User user){
        userRepository.save(user);
    }

    public User findUserById(Long id) {
        return userRepository.findUserById(id).orElseThrow(() ->
                new NotFoundException("User não encontrado"));
    }

    public UserDTO getUserById(Long id) {
        User user = findUserById(id);
        return modelMapper.map(user, UserDTO.class);
    }

    public List<UserDTO> getAllUsers(){
        List<User> userList = userRepository.findAll();
        return userList.stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
    }

}

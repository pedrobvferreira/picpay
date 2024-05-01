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

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;

    public void validateTransaction(User sender, BigDecimal amount) throws Exception {
        if(sender.getUserType() == UserTypeEnum.MERCHANT){
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
        UserDTO userDTO = new UserDTO();
        modelMapper.map(user, userDTO);
        return userDTO;
    }

    public List<UserDTO> getAllUsers(){
        List<User> userList = userRepository.findAll();
        List<UserDTO> userDTOList = new ArrayList<>();
        modelMapper.map(userList, userDTOList);
        return userDTOList;
    }

}

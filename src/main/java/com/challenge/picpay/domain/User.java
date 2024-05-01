package com.challenge.picpay.domain;

import com.challenge.picpay.domain.enums.UserTypeEnum;
import com.challenge.picpay.dto.UserDTO;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(unique = true)
    private String cpf;
    @Column(unique = true)
    private String email;
    private String pass;
    private BigDecimal balance;
    private UserTypeEnum userType;

    public User() {};

    public User(UserDTO data){
        this.name = data.getName();
        this.balance = data.getBalance();
        this.userType = UserTypeEnum.valueOf(data.getUserType());
        this.pass = data.getPass();
        this.cpf = data.getCpf();
        this.email = data.getEmail();
    }
}

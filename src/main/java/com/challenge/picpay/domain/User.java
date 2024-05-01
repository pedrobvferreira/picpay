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
    @Column(name = "user_type")
    private UserTypeEnum userType;
}

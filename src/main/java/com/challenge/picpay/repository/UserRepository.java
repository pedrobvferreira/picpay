package com.challenge.picpay.repository;

import com.challenge.picpay.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByCpf(String cpf);

    Optional<User> findUserById(Long id);
}

package com.challenge.picpay.service;

import com.challenge.picpay.domain.Transaction;
import com.challenge.picpay.domain.User;
import com.challenge.picpay.dto.TransactionDTO;
import com.challenge.picpay.exception.UnauthorizedException;
import com.challenge.picpay.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@AllArgsConstructor
@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private AuthorizationService authService;
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private ModelMapper modelMapper;

    public TransactionDTO createTransaction(TransactionDTO transaction) throws Exception {
        Transaction trans = saveTransaction(transaction);
        modelMapper.map(trans, transaction);
        return transaction;
    }

    @Transactional
    public Transaction saveTransaction(TransactionDTO transaction) throws Exception {
        //1- Validar
        User sender = userService.findUserById(transaction.getSenderId());
        User receiver = userService.findUserById(transaction.getReceiverId());

        userService.validateTransaction(sender, transaction.getValue());

        //2- Autorizar a Transacao
        boolean isAuthorized = authService.authorizeTransaction(sender, transaction.getValue());
        if(!isAuthorized){
            throw new UnauthorizedException("Transação não autorizada");
        }

        //3- Criar a Transacao
        Transaction newTransaction = new Transaction();
        newTransaction.setAmount(transaction.getValue());
        newTransaction.setSender(sender);
        newTransaction.setReceiver(receiver);
        newTransaction.setCreatedAt(LocalDateTime.now());

        sender.setBalance(sender.getBalance().subtract(transaction.getValue()));
        receiver.setBalance(receiver.getBalance().add(transaction.getValue()));

        transactionRepository.save(newTransaction);
        userService.saveUser(sender);
        userService.saveUser(receiver);

        //4- Enviar a notificacao
        notificationService.sendNotification(sender, "Transação realizada com sucesso");
        notificationService.sendNotification(receiver, "Transação recebida com sucesso");

        return newTransaction;
    }
}

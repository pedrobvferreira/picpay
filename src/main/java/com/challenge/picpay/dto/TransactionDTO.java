package com.challenge.picpay.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDTO {
    private BigDecimal value;
    private Long senderId;
    private Long receiverId;
}

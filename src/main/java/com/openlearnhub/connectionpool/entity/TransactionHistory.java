package com.openlearnhub.connectionpool.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TransactionHistory{
    private Long id ;
    private Long accountNumberTransfer;
    private Long accountNumverRecieve;
    private double amount;
    private DateTimeFormat date;
}

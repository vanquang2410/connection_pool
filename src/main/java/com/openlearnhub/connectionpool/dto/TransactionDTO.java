package com.openlearnhub.connectionpool.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDTO {
    private  Long accountNumberTransfer;
    private  Long accountNumberRecieve;
    private  double amount ;
}

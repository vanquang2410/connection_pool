package com.openlearnhub.connectionpool.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AccountBank {
    private Long accountNumber ;
    private double balance;
    private String name ;
}

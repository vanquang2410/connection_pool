package com.openlearnhub.connectionpool.controller;

import com.openlearnhub.connectionpool.dto.TransactionDTO;
import com.openlearnhub.connectionpool.service.impl.AccountBankServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController()
@RequestMapping("/api/transaction")
public class TransactionController {
    @Autowired
    private AccountBankServiceImpl accountBankService;

    @PostMapping("")
    public String transaction(@RequestBody TransactionDTO transactionDTO) throws SQLException {
        return accountBankService.transaction(transactionDTO.getAccountNumberTransfer(),transactionDTO.getAccountNumberRecieve(),transactionDTO.getAmount());
    }
}

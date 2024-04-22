package com.openlearnhub.connectionpool.service;

import com.openlearnhub.connectionpool.entity.AccountBank;

import java.sql.SQLException;

public interface AccountBankService {
    AccountBank getInfo(Long id);

    String transaction (Long accountNumberTransfer, Long accountNumberRecieve, double amount) throws SQLException;
}

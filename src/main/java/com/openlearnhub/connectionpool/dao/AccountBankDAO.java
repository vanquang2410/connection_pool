package com.openlearnhub.connectionpool.dao;

import com.openlearnhub.connectionpool.entity.AccountBank;

import java.sql.SQLException;

public interface AccountBankDAO {
     AccountBank getInfo(Long id);


     void transaction(Long accountNumberTransfer, Long accountNumberRecieve, double amount) throws SQLException;
}

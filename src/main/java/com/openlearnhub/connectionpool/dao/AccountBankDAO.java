package com.openlearnhub.connectionpool.dao;

import com.openlearnhub.connectionpool.entity.AccountBank;

import java.sql.SQLException;

public interface AccountBankDAO {
     AccountBank getInfo(Long id);

     void deposit(Long id ,double amount) throws SQLException;

     void withdraw(Long id, double amount);
}

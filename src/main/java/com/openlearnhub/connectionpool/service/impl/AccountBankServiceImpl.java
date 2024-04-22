package com.openlearnhub.connectionpool.service.impl;

import com.openlearnhub.connectionpool.dao.AccountBankDAO;
import com.openlearnhub.connectionpool.dao.impl.AccountBankDAOImpl;
import com.openlearnhub.connectionpool.entity.AccountBank;
import com.openlearnhub.connectionpool.service.AccountBankService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.SQLException;

@Service
public class AccountBankServiceImpl implements AccountBankService {
    private  final AccountBankDAO accountBankDAO=new AccountBankDAOImpl();

    @Override
    public AccountBank getInfo(Long id) {
        return accountBankDAO.getInfo(id);
    }

    @Override
    @Transactional
    public String transaction(Long accountNumberTransfer, Long accountNumberRecieve, double amount) throws SQLException {

        accountBankDAO.withdraw(accountNumberTransfer,amount);
        accountBankDAO.deposit(accountNumberRecieve,amount);
        return "giao dich thanh cong";
    }
}

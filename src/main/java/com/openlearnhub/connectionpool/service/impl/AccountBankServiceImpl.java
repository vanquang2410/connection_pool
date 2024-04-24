package com.openlearnhub.connectionpool.service.impl;

import com.openlearnhub.connectionpool.dao.AccountBankDAO;
import com.openlearnhub.connectionpool.dao.impl.AccountBankDAOImpl;
import com.openlearnhub.connectionpool.entity.AccountBank;
import com.openlearnhub.connectionpool.service.AccountBankService;
import com.openlearnhub.connectionpool.util.ConnectionPool;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Connection;
import java.sql.SQLException;

@Service
public class AccountBankServiceImpl implements AccountBankService {
    private   AccountBankDAO accountBankDAO=new AccountBankDAOImpl();

    @Override
    public AccountBank getInfo(Long id) {
        return accountBankDAO.getInfo(id);
    }

    @Override
    public String transaction(Long accountNumberTransfer, Long accountNumberRecieve, double amount) throws SQLException {
       accountBankDAO.transaction(accountNumberTransfer,accountNumberRecieve,amount);
       return "giao dich thanh cong";
    }
}

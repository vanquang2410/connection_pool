package com.openlearnhub.connectionpool.dao.impl;

import com.openlearnhub.connectionpool.dao.AccountBankDAO;
import com.openlearnhub.connectionpool.entity.AccountBank;
import com.openlearnhub.connectionpool.util.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountBankDAOImpl  implements AccountBankDAO {
    @Override
    public AccountBank getInfo(Long id){
        String sql ="select * from account_bank where account_number =?";
        try(Connection connection= ConnectionPool.getInstance().getConnection()) {
            PreparedStatement statement= connection.prepareStatement(sql);
            statement.setLong(1,id);
            try(ResultSet resultSet = statement.executeQuery()){
                if (resultSet.next()) {
                    return new AccountBank(
                            resultSet.getLong("account_number"),
                            resultSet.getDouble("balance"),
                            resultSet.getString("name")
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void deposit(Long id, double amount)  {
        String sql="UPDATE account_bank SET balance = balance + ? WHERE account_number =?" ;
        try(Connection connection =ConnectionPool.getInstance().getConnection()) {
            PreparedStatement statement= connection.prepareStatement(sql);
            statement.setDouble(1,amount);
            statement.setLong(2,id);
            int resultSet = statement.executeUpdate();
            connection.commit();
            if (resultSet>0){
                System.out.println("nhan tien thanh cong");
            }
            else System.out.println("giao dich that bai");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void withdraw(Long id, double amount) {
        String sql="UPDATE account_bank SET balance = balance - ? WHERE account_number =?" ;
        try(Connection connection =ConnectionPool.getInstance().getConnection()) {
            PreparedStatement statement= connection.prepareStatement(sql);
            statement.setDouble(1,amount);
            statement.setLong(2,id);
            int resultSet = statement.executeUpdate();
            connection.commit();
            if (resultSet>0){
                System.out.println("chuyen tien thanh cong");
            }
            else System.out.println("giao dich that bai");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}

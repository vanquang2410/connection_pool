package com.openlearnhub.connectionpool.dao.impl;

import com.openlearnhub.connectionpool.dao.AccountBankDAO;
import com.openlearnhub.connectionpool.entity.AccountBank;
import com.openlearnhub.connectionpool.util.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

import static com.openlearnhub.connectionpool.contain.SqlQuery.DEPOSIT;
import static com.openlearnhub.connectionpool.contain.SqlQuery.WITHDRAW;


public class AccountBankDAOImpl  implements AccountBankDAO {

    @Override
    public void transaction(Long accountNumberTransfer, Long accountNumberRecieve, double amount) throws SQLException {
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();


            withdraw(connection, accountNumberTransfer, amount);

            deposit(connection, accountNumberRecieve, amount);

            connection.commit();
        } catch (SQLException e) {
            // Nếu có lỗi xảy ra, hủy bỏ giao dịch
            if (connection != null) {
                connection.rollback();
            }
            throw e;
        } finally {
            // Đảm bảo đóng kết nối sau khi giao dịch kết thúc
            if (connection != null) {
                connection.setAutoCommit(true); // Đặt lại chế độ tự động commit
                connection.close();
            }
        }
    }

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


    private void deposit(Connection connection,Long id, double amount)  {
        PreparedStatement statement=null;
        try {
            statement= connection.prepareStatement(DEPOSIT);
            statement.setDouble(1,amount);
            statement.setLong(2,id);
            int resultSet = statement.executeUpdate();
            if (resultSet>0){
                System.out.println("nhan tien thanh cong");
            }
            else System.out.println("giao dich that bai");
        } catch (SQLException e) {
            handleSQLException(e, connection);
        } finally {
            closeStatement(statement);
        }

    }


    private void withdraw(Connection connection, Long id, double amount) {
        if(isValidAccount(connection,id,amount)) {
            PreparedStatement statement=null;
            try {
                statement= connection.prepareStatement(WITHDRAW);
                statement.setDouble(1,amount);
                statement.setLong(2,id);
                int resultSet = statement.executeUpdate();
                if (resultSet>0){
                    System.out.println("chuyen tien thanh cong");
                }
                else System.out.println("giao dich that bai");
            } catch (SQLException e) {
                handleSQLException(e, connection);
            } finally {
                closeStatement(statement);
            }
        }
        else {
            throw new IllegalArgumentException("Tài khoản không hợp lệ hoặc không đủ tiền");
        }
    }

    private static boolean isValidAccount(Connection connection,Long accountId,Double amount) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        boolean isValid = false;
        try {
            String sql = "SELECT * FROM account_bank WHERE account_number = ?";
            statement = connection.prepareStatement(sql);
            statement.setLong(1, accountId);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                double currentBalance = resultSet.getDouble("balance");
                if(currentBalance>amount){
                    isValid=true;
                }

            }
        } catch (SQLException e) {
            handleSQLException(e, connection);
        } finally {
            closeStatement(statement);
        }

        return isValid;
    }
    private static void closeStatement(PreparedStatement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    private static void handleSQLException(SQLException e, Connection connection) {
        if (connection != null) {
            try {
                connection.rollback(); // Rollback giao dịch nếu có lỗi
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        e.printStackTrace(); // In ra lỗi ra console hoặc log
    }

}

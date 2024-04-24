package com.openlearnhub.connectionpool.contain;

public class SqlQuery {
    public  static String DEPOSIT="UPDATE account_bank SET balance = balance + ? WHERE account_number =?";
    public  static String WITHDRAW="UPDATE account_bank SET balance = balance - ? WHERE account_number =?";
}

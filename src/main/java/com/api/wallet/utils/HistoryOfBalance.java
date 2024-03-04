package com.api.wallet.utils;

import com.api.wallet.db.ConnectionDB;
import com.api.wallet.db.entity.Account;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class HistoryOfBalance {
    public static void getHistoryOfBalanceOfAccount(LocalDateTime HistoryStartDate, LocalDateTime historyEndDate, Account account){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            String sql = "select history.balance from history \n" +
                    "inner join account ON history.accountId = account.accountId \n" +
                    "inner join transaction ON history.transactionId = transaction.transactionId \n" +
                    "where account.accountId = ? AND transaction.date BETWEEN ? AND ?";
            connection = ConnectionDB.createConnection();
            statement = connection.prepareStatement(sql);
            statement.setObject(1,account.getAccountId());
            statement.setObject(2,HistoryStartDate);
            statement.setObject(3,historyEndDate);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                BigDecimal balance = resultSet.getBigDecimal("balance");
                System.out.println(balance);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            try {
                if (resultSet != null){
                    resultSet.close();
                }
                if (statement != null){
                    statement.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                if (connection != null ){
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

}

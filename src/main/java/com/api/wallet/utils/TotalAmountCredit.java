package com.api.wallet.utils;

import com.api.wallet.db.ConnectionDB;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class TotalAmountCredit {
    public static BigDecimal getTotalAmountCredit(String id , LocalDateTime start , LocalDateTime end){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        BigDecimal result = null;
        try{
            String sql = "select sum(transaction.amount) as Total_amount_credit from transaction inner join account ON transaction.accountId = account.accountId where account.accountId = ? AND  transaction.type = 'CREDIT'  AND transaction.date BETWEEN ? AND ? GROUP BY transaction.type ";
            connection = ConnectionDB.createConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1,id);
            statement.setObject(2,start);
            statement.setObject(3,end);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                result =  resultSet.getBigDecimal("Total_amount_credit");
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
        return result;
    }

}

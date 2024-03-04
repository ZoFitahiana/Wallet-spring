package com.api.wallet.utils.function_sql;

import com.api.wallet.db.ConnectionDB;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class TotalAmountByTypeTransactionSqlFunction {
    public static  void getTotalAmountByTypeTransactionSqlFunction(String id , LocalDateTime startDate , LocalDateTime endDate){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            String sql = "SELECT * FROM getTotalAmount(?,?,?)";
            connection = ConnectionDB.createConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1,id);
            statement.setObject(2,startDate);
            statement.setObject(3,endDate);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                BigDecimal Total_Amount_Credit = resultSet.getBigDecimal("TotalAmountCredit");
                BigDecimal Total_Amount_Debit = resultSet.getBigDecimal("TotalAmountDebit");
                System.out.println("Total_Amount_Credit : "+ Total_Amount_Credit +  " , Total_Amount_Debit : " + Total_Amount_Debit);
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


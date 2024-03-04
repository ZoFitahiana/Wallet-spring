package com.api.wallet.utils.transaction;

import com.api.wallet.db.ConnectionDB;
import com.api.wallet.db.entity.Transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SaveHistoryTransaction {
    public static void registerTransactionHistory(Transaction creditor, Transaction debtor){
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            String sql = "insert into TransferHistory (creditorTransactionId,debtorTransactionId,dateOfTransaction) values (?,?,?)";
            connection = ConnectionDB.createConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1,creditor.getTransactionId());
            statement.setString(2,debtor.getTransactionId());
            statement.setObject(3,creditor.getDate());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            try {
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

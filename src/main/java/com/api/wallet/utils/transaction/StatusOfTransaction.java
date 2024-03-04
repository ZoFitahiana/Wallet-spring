package com.api.wallet.utils.transaction;
import com.api.wallet.db.ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class StatusOfTransaction {
    public static  void statusOfTransaction(LocalDateTime start ,LocalDateTime end){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            String sql = "SELECT  DISTINCT  subquery1.Account_debtor_id,subquery2.Account_creditor_id,subquery2.Amount,subquery2.dateoftransaction\n" +
                    "FROM \n" +
                    "   (SELECT account.accountId AS Account_debtor_id FROM  account INNER JOIN  transaction ON transaction.accountId = account.accountId INNER JOIN TransferHistory ON transaction.transactionId = TransferHistory.creditortransactionid) subquery1,\n" +
                    "   (SELECT account.accountId AS Account_creditor_id,transaction.amount AS Amount,TransferHistory.dateoftransaction\n" +
                    "FROM \n" +
                    "    account\n" +
                    "    INNER JOIN transaction ON transaction.accountId = account.accountId\n" +
                    "    INNER JOIN  TransferHistory ON transaction.transactionId = TransferHistory.debtortransactionid\n" +
                    "    WHERE TransferHistory.dateoftransaction BETWEEN ? AND ? "+
                    "    GROUP BY TransferHistory.dateoftransaction,account.accountId,transaction.amount) subquery2;\n";
            connection = ConnectionDB.createConnection();
            statement = connection.prepareStatement(sql);
            statement.setObject(1,start);
            statement.setObject(2,end);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                String debtorId = resultSet.getString("account_debtor_id");
                String creditorId = resultSet.getString("account_creditor_id");
                double amount = resultSet.getDouble("amount");
                LocalDateTime dateOfTransaction = resultSet.getTimestamp("dateoftransaction").toLocalDateTime();
                System.out.println("Debtor id : " + debtorId + ", Creditor id : "+creditorId + ", Amount : "+ amount + ", Date of transaction  : "+ dateOfTransaction);
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

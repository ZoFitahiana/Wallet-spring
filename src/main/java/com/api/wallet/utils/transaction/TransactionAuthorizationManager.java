package com.api.wallet.utils.transaction;

import com.api.wallet.db.ConnectionDB;
import com.api.wallet.db.entity.Account;
import com.api.wallet.db.entity.Transaction;
import com.api.wallet.operation.TransactionCrudOperations;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class TransactionAuthorizationManager {
    public static void authorizeTransaction(Account account , Transaction transaction){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String typeCategories = TypeOfCategoryOfTransaction.getTypeCategories(transaction.getCategoriesId());
        if ((transaction.getType().equals("DEBIT") && Objects.equals(typeCategories, "EXPENSE")) || ((transaction.getType().equals("CREDIT") && (Objects.equals(typeCategories, "INCOME"))))){
            if (transaction.getType().equals("DEBIT") && account.getBalance().compareTo(transaction.getAmount()) < 0 ) {
                if (account.getType().equals("Bank")) {
                    BigDecimal newBalance = account.getBalance().subtract(transaction.getAmount());
                    try {
                        String sql = "INSERT INTO transaction (transactionId, amount, label, type, date,accountId,categoriesId) " +
                                "VALUES (?, ?, ?, ?, ?,?,?) " +
                                "ON CONFLICT (transactionId) " +
                                "DO UPDATE SET amount = EXCLUDED.amount, label = EXCLUDED.label, " +
                                "type = EXCLUDED.type, date = EXCLUDED.date , accountId = EXCLUDED.accountId , categoriesId = EXCLUDED.categoriesId";

                        connection = ConnectionDB.createConnection();
                        statement = connection.prepareStatement(sql);
                        statement.setString(1, transaction.getTransactionId());
                        statement.setBigDecimal(2, transaction.getAmount());
                        statement.setString(3, transaction.getLabel());
                        statement.setString(4, transaction.getType());
                        statement.setObject(5, transaction.getDate());
                        statement.setString(6, transaction.getAccountId());
                        statement.setString(7, transaction.getCategoriesId());
                        statement.executeUpdate();

                        String SQL = "update account set balance = ? WHERE accountId = ?";
                        statement = connection.prepareStatement(SQL);
                        statement.setBigDecimal(1,newBalance);
                        statement .setString(2, account.getAccountId());
                        statement.executeUpdate();
                        TransactionCrudOperations transactions = new TransactionCrudOperations();
                        transactions.findById(transaction.getTransactionId());

                        String historySql = "insert into history (accountId,transactionId,balance) values(?,?,?)";
                        statement = connection.prepareStatement(historySql);
                        statement.setString(1,account.getAccountId());
                        statement.setString(2,transaction.getTransactionId());
                        statement.setBigDecimal(3,account.getBalance());
                        statement.executeUpdate();

                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }

                }else{
                    throw  new RuntimeException("Your balance is insufficient");
                }
            }
            else{
                switch (transaction.getType()){
                    case "DEBIT" :
                        BigDecimal newBalance = account.getBalance().subtract(transaction.getAmount());
                        try {
                            String sql = "INSERT INTO transaction (transactionId, amount, label, type, date,accountId,categoriesId) " +
                                    "VALUES (?, ?, ?, ?, ?,?,?) " +
                                    "ON CONFLICT (transactionId) " +
                                    "DO UPDATE SET amount = EXCLUDED.amount, label = EXCLUDED.label, " +
                                    "type = EXCLUDED.type, date = EXCLUDED.date , accountId = EXCLUDED.accountId , categoriesId = EXCLUDED.categoriesId";
                            connection = ConnectionDB.createConnection();
                            statement = connection.prepareStatement(sql);
                            statement.setString(1, transaction.getTransactionId());
                            statement.setBigDecimal(2, transaction.getAmount());
                            statement.setString(3, transaction.getLabel());
                            statement.setString(4, transaction.getType());
                            statement.setObject(5, transaction.getDate());
                            statement.setString(6, transaction.getAccountId());
                            statement.setString(7,transaction.getCategoriesId());
                            statement.executeUpdate();

                            String SQL = "update account set balance = ? WHERE accountId = ?";
                            statement = connection.prepareStatement(SQL);
                            statement.setBigDecimal(1,newBalance);
                            statement.setString(2, account.getAccountId());
                            statement.executeUpdate();
                            TransactionCrudOperations transactions = new TransactionCrudOperations();
                            transactions.findById(transaction.getTransactionId());


                            String historySql = "insert into history (accountId,transactionId,balance) values(?,?,?)";
                            statement = connection.prepareStatement(historySql);
                            statement.setString(1,account.getAccountId());
                            statement.setString(2,transaction.getTransactionId());
                            statement.setBigDecimal(3,account.getBalance());
                            statement.executeUpdate();

                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                        break;
                    case "CREDIT":
                        BigDecimal newBalances = account.getBalance().add(transaction.getAmount()) ;
                        try {
                            String sql = "INSERT INTO transaction (transactionId, amount, label, type, date,accountId,categoriesId) " +
                                    "VALUES (?, ?, ?, ?, ?,?,?) " +
                                    "ON CONFLICT (transactionId) " +
                                    "DO UPDATE SET amount = EXCLUDED.amount, label = EXCLUDED.label, " +
                                    "type = EXCLUDED.type, date = EXCLUDED.date , accountId = EXCLUDED.accountId ,categoriesId = EXCLUDED.categoriesId";
                            connection = ConnectionDB.createConnection();
                            statement = connection.prepareStatement(sql);
                            statement.setString(1, transaction.getTransactionId());
                            statement.setBigDecimal(2, transaction.getAmount());
                            statement.setString(3, transaction.getLabel());
                            statement.setString(4, transaction.getType());
                            statement.setObject(5, transaction.getDate());
                            statement.setString(6, transaction.getAccountId());
                            statement.setString(7,transaction.getCategoriesId());
                            statement.executeUpdate();

                            String SQL = "update account set balance = ? WHERE accountId = ?";
                            statement = connection.prepareStatement(SQL);
                            statement.setBigDecimal(1,newBalances);
                            statement.setString(2, account.getAccountId());
                            statement.executeUpdate();
                            TransactionCrudOperations transactions = new TransactionCrudOperations();
                            transactions.findById(transaction.getTransactionId());


                            String historySql = "insert into history (accountId,transactionId,balance) values(?,?,?)";
                            statement = connection.prepareStatement(historySql);
                            statement.setString(1,account.getAccountId());
                            statement.setString(2,transaction.getTransactionId());
                            statement.setBigDecimal(3,account.getBalance());
                            statement.executeUpdate();

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
                        break;
                }
            }

        }else{
            throw  new RuntimeException("Verify your type categories or type transaction");
        }
    }
}
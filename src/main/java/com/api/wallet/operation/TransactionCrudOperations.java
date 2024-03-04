package com.api.wallet.operation;

import com.api.wallet.db.ConnectionDB;
import com.api.wallet.db.entity.Account;
import com.api.wallet.db.entity.Transaction;
import com.api.wallet.utils.transaction.TransactionAuthorizationManager;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Repository

public class TransactionCrudOperations implements CrudOperations<Transaction>{
    @Override
    public Transaction findById(String id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Transaction transaction = null;

        try{
            String sql = "select * from transaction where transactionId = ?";
            connection = ConnectionDB.createConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1,id);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                String transactionId = resultSet.getString("transactionId");
                BigDecimal amount = resultSet.getBigDecimal("amount");
                String label = resultSet.getString("label");
                String type = resultSet.getString("type");
                LocalDateTime date = resultSet.getTimestamp("date").toLocalDateTime();
                String accountId = resultSet.getString("accountId");
                String categoriesId = resultSet.getString("categoriesId");
                transaction = new Transaction(transactionId,amount,label,type,date,accountId,categoriesId);
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
        return transaction ;
    }

    @Override
    public List<Transaction> findAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Transaction> transactionList = new ArrayList<>();
        try{
            String sql = "select * from transaction ";
            connection = ConnectionDB.createConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                String transactionId = resultSet.getString("transactionId");
                BigDecimal amount = resultSet.getBigDecimal("amount");
                String label = resultSet.getString("label");
                String type = resultSet.getString("type");
                LocalDateTime date = resultSet.getTimestamp("date").toLocalDateTime();
                String accountId = resultSet.getString("accountId");
                String categoriesId = resultSet.getString("categoriesId");
                Transaction transaction = new Transaction(transactionId,amount,label,type,date,accountId,categoriesId);
                transactionList.add(transaction);
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
        return transactionList;
    }

    @Override
    public Transaction save(Transaction toSave) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            String sql = "select * from account where accountId = ? ";
            connection = ConnectionDB.createConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1,toSave.getAccountId());
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                String accountId = resultSet.getString("accountId");
                String name = resultSet.getString("name");
                BigDecimal balance = resultSet.getBigDecimal("balance");
                LocalDateTime lastUpdate = resultSet.getTimestamp("lastUpdate").toLocalDateTime();
                AccountCrudOperations accountCrudOperation = new AccountCrudOperations();
                List<Transaction> transactionList = accountCrudOperation.getTransactionsForAccount(resultSet.getString("accountId"));
                String currencyId = resultSet.getString("currencyId");
                String type = resultSet.getString("type");
                Account account = new Account(accountId,name,balance,lastUpdate,transactionList,currencyId,type);
                TransactionAuthorizationManager.authorizeTransaction(account, toSave);
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

        return toSave;
    }

    @Override
    public List<Transaction> saveAll(List<Transaction> toSave) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            for (Transaction transaction : toSave) {
                String sql = "select * from account where accountId = ? ";
                connection = ConnectionDB.createConnection();
                statement = connection.prepareStatement(sql);
                statement.setString(1, transaction.getAccountId());
                resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    String accountId = resultSet.getString("accountId");
                    String name = resultSet.getString("name");
                    BigDecimal balance = resultSet.getBigDecimal("balance");
                    LocalDateTime lastUpdate = resultSet.getTimestamp("lastUpdate").toLocalDateTime();
                    AccountCrudOperations accountCrudOperation = new AccountCrudOperations();
                    List<Transaction> transactionList = accountCrudOperation.getTransactionsForAccount(resultSet.getString("accountId"));
                    String currencyId = resultSet.getString("currencyId");
                    String type = resultSet.getString("type");
                    Account account = new Account(accountId, name, balance, lastUpdate, transactionList, currencyId, type);
                    TransactionAuthorizationManager.authorizeTransaction(account, transaction);
                }
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
        return toSave;
    }

    @Override
    public Transaction update(Transaction toUpdate) {
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            String sql = "update transaction set amount = ? , label = ? , type = ? , date = ? , accountId = ?,categoriesId = ? where  transactionId = ? ";
            connection = ConnectionDB.createConnection();
            statement = connection.prepareStatement(sql);
            statement.setBigDecimal(1,toUpdate.getAmount());
            statement.setString(2,toUpdate.getLabel());
            statement.setString(3,toUpdate.getType());
            statement.setObject(4,toUpdate.getDate());
            statement.setString(5,toUpdate.getAccountId());
            statement.setString(6,toUpdate.getCategoriesId());
            statement.setString(7,toUpdate.getTransactionId());

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected == 0) {
                throw new RuntimeException("Account with accountId " + toUpdate.getTransactionId() + " not found");
            }
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
        return findById(toUpdate.getTransactionId());
    }

}

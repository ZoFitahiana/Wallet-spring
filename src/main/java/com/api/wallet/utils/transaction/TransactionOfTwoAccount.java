package com.api.wallet.utils.transaction;
import com.api.wallet.db.ConnectionDB;
import com.api.wallet.db.entity.Account;
import com.api.wallet.db.entity.Transaction;
import com.api.wallet.operation.AccountCrudOperations;
import com.api.wallet.operation.TransactionCrudOperations;
import com.api.wallet.utils.transaction.SaveHistoryTransaction;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;


public class TransactionOfTwoAccount{
    public static void transactionOfAccount(Transaction creditorAccount , Transaction debitorAccount) {
        if (creditorAccount.equals(debitorAccount)){
            throw new RuntimeException("Transaction invalid");
            }else{
            Connection connection = null;
            PreparedStatement statement = null;
            ResultSet resultSet = null;
            try {
                String sql = "select * from account where accountId = ? ";
                connection = ConnectionDB.createConnection();
                statement = connection.prepareStatement(sql);
                statement.setString(1,creditorAccount.getAccountId());
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
                    if (account.getBalance().compareTo(creditorAccount.getAmount()) < 0){
                        if (account.getType().equals("Bank")){
                           TransactionCrudOperations transactions = new TransactionCrudOperations();
                            transactions.save(creditorAccount);
                            transactions.save(debitorAccount);
                            SaveHistoryTransaction.registerTransactionHistory(creditorAccount,debitorAccount);
                        }
                        else {
                            throw new RuntimeException("Echec transaction ! , your balance is insufficient");
                        }

                    }
                    else{
                        TransactionCrudOperations transactions = new TransactionCrudOperations();
                        transactions.save(creditorAccount);
                        transactions.save(debitorAccount);
                        SaveHistoryTransaction.registerTransactionHistory(creditorAccount,debitorAccount);
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
        }

    }
}

package com.api.wallet.db.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class History implements Serializable {
    private String historyId ;
    private String accountId ;
    private String transactionId ;
    private BigDecimal balance ;

    public History(String historyId, String accountId, String transactionId, BigDecimal balance) {
        this.historyId = historyId;
        this.accountId = accountId;
        this.transactionId = transactionId;
        this.balance = balance;
    }

    public String getHistoryId() {
        return historyId;
    }

    public void setHistoryId(String historyId) {
        this.historyId = historyId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        History history = (History) object;
        return Objects.equals(historyId, history.historyId) && Objects.equals(accountId, history.accountId) && Objects.equals(transactionId, history.transactionId) && Objects.equals(balance, history.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(historyId, accountId, transactionId, balance);
    }

    @Override
    public String toString() {
        return "History{" +
                "historyId='" + historyId + '\'' +
                ", accountId='" + accountId + '\'' +
                ", transactionId='" + transactionId + '\'' +
                ", balance=" + balance +
                '}';
    }
}

package com.api.wallet.db.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class Account implements Serializable {
    private String accountId;
    private String name;
    private BigDecimal balance;
    private LocalDateTime lastUpdate;
    private List<Transaction> transactionList;
    private String currencyId;
    private String type;


    public Account(String accountId, String name, BigDecimal balance, LocalDateTime lastUpdate, List<Transaction> transactionList, String currencyId, String type) {
        this.accountId = accountId;
        this.name = name;
        this.balance = balance;
        this.lastUpdate = lastUpdate;
        this.transactionList = transactionList;
        this.currencyId = currencyId;
        this.type = type;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

    public String getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(String currencyId) {
        this.currencyId = currencyId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Account account = (Account) object;
        return Objects.equals(accountId, account.accountId) && Objects.equals(name, account.name) && Objects.equals(balance, account.balance) && Objects.equals(lastUpdate, account.lastUpdate) && Objects.equals(transactionList, account.transactionList) && Objects.equals(currencyId, account.currencyId)  && Objects.equals(type, account.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId, name, balance, lastUpdate, transactionList, currencyId,type);
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId='" + accountId + '\'' +
                ", name='" + name + '\'' +
                ", balance=" + balance +
                ", lastUpdate=" + lastUpdate +
                ", transactionList=" + transactionList +
                ", currencyId='" + currencyId + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}

package com.api.wallet.service;

import com.api.wallet.db.entity.Account;
import com.api.wallet.operation.AccountCrudOperations;
import org.springframework.stereotype.Service;

import java.util.List;
@Service

public class AccountService {
    private final AccountCrudOperations  accountCrudOperations;

    public AccountService(AccountCrudOperations accountCrudOperations) {
        this.accountCrudOperations = accountCrudOperations;
    }


    public Account getAccount(String id){
        return accountCrudOperations.findById(id);
    }

    public List<Account> getAccounts(){
        return accountCrudOperations.findAll();
    }
    public Account register(Account account){
        return accountCrudOperations.save(account);
    }

    public List<Account> saveList(List<Account> accountList){
        return  accountCrudOperations.saveAll(accountList);
    }

    public Account Update(Account account){
        return accountCrudOperations.update(account);
    }

}

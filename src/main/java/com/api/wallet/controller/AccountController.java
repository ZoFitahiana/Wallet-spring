package com.api.wallet.controller;

import com.api.wallet.db.entity.Account;
import com.api.wallet.db.entity.Transaction;
import com.api.wallet.service.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class AccountController {
   private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/accounts")
    public List<Account> getAccounts(){
        return accountService.getAccounts();
    }
    @GetMapping("/account/{id}")
    public Account getAccount(@PathVariable String id){
        return accountService.getAccount(id);
    }
    @PostMapping("/account")
    public Account register(@RequestBody Account account){
        return accountService.register(account);
    }
    @PostMapping("/accounts")
    public List<Account> saveAll(@RequestBody List<Account> accountList){
        return accountService.saveList(accountList);
    }
    @PutMapping("/account")
    public Account update(@RequestBody Account account){
        return accountService.Update(account);
    }
}

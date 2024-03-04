package com.api.wallet.controller;

import com.api.wallet.db.entity.Transaction;
import com.api.wallet.service.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class TransactionController {
    private  final  TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/transaction/{id}")
    public Transaction getTransaction(@PathVariable String id){
        return transactionService.getTransaction(id);
    }
    @GetMapping("/transactions")
    public List<Transaction> getTransactions(){
        return transactionService.getTransactions();
    }
    @PostMapping("/transaction")
    public Transaction save(@RequestBody Transaction transaction){
        return transactionService.register(transaction);
    }
    @PostMapping("/transactions")
    public List<Transaction> saveAll(@RequestBody  List<Transaction> transactions){
        return transactionService.saveAll(transactions);
    }
    @PutMapping("/transaction")
    public Transaction update(@RequestBody Transaction transaction){
        return transactionService.update(transaction);
}
}

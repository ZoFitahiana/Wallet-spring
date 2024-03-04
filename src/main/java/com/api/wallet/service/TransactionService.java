package com.api.wallet.service;

import com.api.wallet.db.entity.Transaction;
import com.api.wallet.operation.TransactionCrudOperations;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class TransactionService {
    private  final  TransactionCrudOperations transactionCrudOperations;

    public TransactionService(TransactionCrudOperations transactionCrudOperations) {
        this.transactionCrudOperations = transactionCrudOperations;
    }

    public Transaction getTransaction(String id){
        return  transactionCrudOperations.findById(id);
    }
    public List<Transaction> getTransactions(){
        return transactionCrudOperations.findAll();
    }
    public  Transaction register(Transaction transaction){
        return  transactionCrudOperations.save(transaction);
    }
    public  List<Transaction> saveAll(List<Transaction> transactionList){
        return  transactionCrudOperations.saveAll(transactionList);
    }
    public  Transaction update(Transaction transaction){
        return  transactionCrudOperations.update(transaction);
    }

}

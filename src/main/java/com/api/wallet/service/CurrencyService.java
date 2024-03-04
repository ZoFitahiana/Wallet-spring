package com.api.wallet.service;
import com.api.wallet.db.entity.Currency;
import com.api.wallet.operation.CurrencyCrudOperation;
import org.springframework.stereotype.Service;

import java.util.List;
@Service

public class CurrencyService {
    private final CurrencyCrudOperation currencyCrudOperation;

    public CurrencyService(CurrencyCrudOperation currencyCrudOperation) {
        this.currencyCrudOperation = currencyCrudOperation;
    }
    public Currency getCurrency(String id){
        return  currencyCrudOperation.findById(id);
    }
    public List<Currency> getAllCurrency(){
        return currencyCrudOperation.findAll();
    }
    public  List<Currency> saveListCurrency(List<Currency> currencyList){
        return  currencyCrudOperation.saveAll(currencyList);
    }
    public Currency saveCurrency(Currency currency){
        return  currencyCrudOperation.save(currency);
    }
    public Currency UpdateCurrency(Currency currency){
        return  currencyCrudOperation.update(currency);
    }
}

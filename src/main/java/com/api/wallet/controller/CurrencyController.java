package com.api.wallet.controller;

import com.api.wallet.db.entity.Currency;
import com.api.wallet.service.CurrencyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class CurrencyController {
    private final CurrencyService currencyService;

    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }
    @GetMapping("/currency/{id}")
    public Currency getCurrency(@PathVariable String id){
        return currencyService.getCurrency(id);
    }
    @GetMapping("/currency")
    public List<Currency> getAllCurrency(){
        return  currencyService.getAllCurrency();
    }
    @PostMapping("/currency-list")
    public List<Currency> saveAll(@RequestBody  List<Currency> currencyList){
        return currencyService.saveListCurrency(currencyList);
    }
    @PostMapping("/currency")
    public Currency saveCurrency(@RequestBody Currency currency){
        return currencyService.saveCurrency(currency);
    }
    @PutMapping("/currency")
    public  Currency updateCurrency(@RequestBody Currency currency){
        return currencyService.UpdateCurrency(currency);
    }


}

package com.api.wallet.utils.transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static com.api.wallet.utils.TotalAmountCredit.getTotalAmountCredit;
import static com.api.wallet.utils.TotalAmountDebit.getTotalAmountDebit;

public class TotalAmountByTypeOfTransaction {
    public static  String getTotalAmountByTypeOfTransaction(String id , LocalDateTime startDate , LocalDateTime endDate){
        BigDecimal credit = getTotalAmountCredit(id,startDate,endDate);
        BigDecimal debit = getTotalAmountDebit(id,startDate,endDate);
        return "Total amount  credit : " + credit + ",  Total amount debit : " + debit;
    }
}

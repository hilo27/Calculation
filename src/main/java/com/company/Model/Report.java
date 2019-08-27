package com.company.Model;
// Created by Руслан on 27.08.2019.

import com.company.ConsoleInput;
import org.apache.commons.lang3.StringUtils;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class Report {
    ConsoleInput input = null;
    Transaction transaction = null;
    List<Transaction> originalTransactionList = null;
    List<Transaction> filteredByDateList = null;

    public Report(List<Transaction> list, ConsoleInput consoleInput) {
        if (!list.isEmpty() && StringUtils.isNotBlank(consoleInput.getUserInput())) {
            this.originalTransactionList = list;
            this.input = consoleInput;
            // sort by date
            originalTransactionList.sort(Comparator.comparing(Transaction::getDate));
            // filter by date before report date
            filteredByDateList = originalTransactionList.parallelStream()
                    .filter(transaction -> transaction.getDate().isBefore(consoleInput.getDate()))
                    .collect(Collectors.toList());
        }
    }


    public String getProfit() {
        AtomicReference<Integer> totalSalesAmount = new AtomicReference<>(0);

        

        // calculate total sales amount
        filteredByDateList.forEach(transaction -> {
            if (transaction instanceof Sale) {
                totalSalesAmount.updateAndGet(v -> v + transaction.getAmount());
            }
        });


        return "dummy";
    }
}

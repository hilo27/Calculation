package com.company.Model;
// Created by Руслан on 27.08.2019.

import com.company.ConsoleInput;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/**
 * This class responsible for generating sales report
 */
public class Report {
    private List<Transaction> filteredByDateList = new ArrayList<>();

    public Report(List<Transaction> list, ConsoleInput consoleInput) {
        if (CollectionUtils.isNotEmpty(list) && StringUtils.isNotBlank(consoleInput.getUserInput())) {
            // sort by date
            list.sort(Comparator.comparing(Transaction::getDate));
            // filter by date before report date
            filteredByDateList = list.parallelStream()
                    .filter(transaction -> transaction.getDate().isBefore(consoleInput.getDate()))
                    .collect(Collectors.toList());
        }
    }

    /**
     * @return - profit report
     */
    public String calculateProfit() {
        // this is calculated total amount of purchase transactions
        AtomicReference<Integer> totalAmount = new AtomicReference<>(0);
        // this is calculated total price of purchase transactions
        BigDecimal totalPrice = BigDecimal.ZERO;
        // it is value represented total price of sales, i know, name could be better.
        AtomicReference<BigDecimal> totalSalesPrice = new AtomicReference<>(BigDecimal.ZERO);

        // calculate total sales amount
        filteredByDateList.forEach(transaction -> {
            if (transaction instanceof Sale) {
                totalAmount.updateAndGet(v -> v + transaction.getAmount());
                totalSalesPrice.updateAndGet(v -> v.add(transaction.getTotal()));
            }
        });
        // temporary value, used for calculating totalPrice
        AtomicReference<Integer> tempTotalAmount = new AtomicReference<>(totalAmount.get());

        // calculating total price
        for (Transaction transaction : filteredByDateList) {
            if (transaction instanceof Purchase) {
                if (transaction.getAmount() > tempTotalAmount.get()) {
                    // if total amount left is lower than transaction amount
                    totalPrice = totalPrice.add(transaction.getPrice().multiply(new BigDecimal(tempTotalAmount.get())));
                    tempTotalAmount.set(0);
                    break;

                } else {
                    // if total amount higher than transaction amount
                    totalPrice = totalPrice.add(transaction.getTotal());
                    tempTotalAmount.updateAndGet(v -> v - transaction.getAmount());
                }
            }
        }

        return String.valueOf(totalSalesPrice.get().subtract(totalPrice));
    }
}

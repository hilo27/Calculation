package com.company.Model;
// Created by Руслан on 27.08.2019.

import java.math.BigDecimal;
import java.time.LocalDate;

@SuppressWarnings("WeakerAccess")
public class Transaction {
    private BigDecimal price;
    private Integer amount;
    private LocalDate date;
    private BigDecimal total = null;

    public BigDecimal getTotal() {
        return total;
    }


    public Transaction(Integer amount, BigDecimal price, LocalDate date) {
        this.amount = amount;
        this.price = price;
        this.date = date;
        total = price.multiply(BigDecimal.valueOf(amount));
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Integer getAmount() {
        return amount;
    }

    public LocalDate getDate() {
        return date;
    }
}

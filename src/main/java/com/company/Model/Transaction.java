package com.company.Model;
// Created by ������ on 27.08.2019.

import java.math.BigDecimal;
import java.time.LocalDate;

@SuppressWarnings("WeakerAccess")
public class Transaction {
    private BigDecimal price;
    private Integer amount;
    private LocalDate date;

    public Transaction(Integer amount, BigDecimal price, LocalDate date) {
        this.amount = amount;
        this.price = price;
        this.date = date;
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

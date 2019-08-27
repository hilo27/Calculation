package com.company.Model;
// Created by Руслан on 27.08.2019.

import java.math.BigDecimal;
import java.time.LocalDate;

@SuppressWarnings("WeakerAccess")
public class Sale extends Transaction {
    private BigDecimal total = null;

    public BigDecimal getTotal() {
        return total;
    }

    public Sale(Integer amount, BigDecimal price, LocalDate date) {
        super(amount, price, date);
        total = price.multiply(BigDecimal.valueOf(amount));
    }
}

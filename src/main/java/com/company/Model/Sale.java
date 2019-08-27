package com.company.Model;
// Created by Руслан on 27.08.2019.

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * This class represent Sales
 */
@SuppressWarnings("WeakerAccess")
public class Sale extends Transaction {

    public Sale(Integer amount, BigDecimal price, LocalDate date) {
        super(amount, price, date);
    }
}

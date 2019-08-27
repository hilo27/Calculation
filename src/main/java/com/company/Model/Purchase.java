package com.company.Model;
// Created by Руслан on 27.08.2019.

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * This class represent consignment of goods
 */
@SuppressWarnings("WeakerAccess")
public class Purchase extends Transaction {

    public Purchase(Integer amount, BigDecimal price, LocalDate date) {
        super(amount, price, date);
    }
}

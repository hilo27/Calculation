package com.company;
// Created by Руслан on 27.08.2019.

import com.company.Model.Purchase;
import com.company.Model.Report;
import com.company.Model.Sale;
import com.company.Model.Transaction;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.company.Constants.RESULT;

/**
 * This class is responsible for the work logic.
 */
@SuppressWarnings("WeakerAccess")
public class Controller {
    private static Map<String, List<Transaction>> goods = new HashMap<>();

    /**
     * Add product in map
     *
     * @return - if product name nat specified or goods already exists then <code>{@link RESULT#ERROR}</code>
     */
    public static String addProduct(ConsoleInput input) {
        if (input.getName() == null || isProductExists(input)) {
            return RESULT.ERROR;
        }

        goods.put(input.getName(), new ArrayList<>());
        return RESULT.OK;
    }

    /**
     * Purchase product
     *
     * @return - <code>{@link RESULT#OK}</code> or <code>{@link RESULT#ERROR}</code>
     */
    public static String purchaseProduct(ConsoleInput input) {
        try {
            if (allowPurchase(input)) {
                addTransaction(input.getName(), new Purchase(input.getAmount(), input.getPrice(), input.getDate()));
                return RESULT.OK;
            }

        } catch (DateTimeParseException ex) {
            return RESULT.ERROR + ": " + ex.getMessage();
        }

        return RESULT.ERROR;
    }

    /**
     * Sale product
     *
     * @return - <code>{@link RESULT#OK}</code> or <code>{@link RESULT#ERROR}</code>
     */
    public static String saleProduct(ConsoleInput input) {
        try {
            if (allowSale(input)) {
                addTransaction(input.getName(), new Sale(input.getAmount(), input.getPrice(), input.getDate()));
                return RESULT.OK;
            }

        } catch (DateTimeParseException ex) {
            return RESULT.ERROR + ": " + ex.getMessage();
        }

        return RESULT.ERROR;
    }

    /**
     * Get sales report
     *
     * @return - <code>{@link RESULT#OK}</code> or <code>{@link RESULT#ERROR}</code>
     */
    public static String salesReport(ConsoleInput input) {
        List<Transaction> transactionList = goods.get(input.getName());
        return new Report(transactionList, input).calculateProfit();
    }

    /**
     * Add transaction in goods list
     */
    private static void addTransaction(String name, Transaction transaction) {
        goods.get(name).add(transaction);
    }

    /**
     * Checking if allow to sale
     */
    private static boolean allowSale(ConsoleInput input) {
        return isProductExists(input); // TODO (Руслан): учитывать дату
    }

    /**
     * Checking if condition are allow purchase
     */
    private static boolean allowPurchase(ConsoleInput input) {
        return isProductExists(input) && input.getPrice() != null;
    }

    /**
     * Check if user specified product name and it is exists in goods map
     */
    private static boolean isProductExists(ConsoleInput input) {
        return goods.get(input.getName()) != null;
    }

}

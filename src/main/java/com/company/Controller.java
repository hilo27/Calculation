package com.company;
// Created by Руслан on 27.08.2019.

import com.company.Model.Purchase;
import com.company.Model.Report;
import com.company.Model.Sale;
import com.company.Model.Transaction;
import org.apache.commons.collections4.CollectionUtils;

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
    public String addProduct(ConsoleInput input) {
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
    public String purchaseProduct(ConsoleInput input) {
        try {
            if (isConditionCorrect(input)) {
                addTransaction(input.getName(), new Purchase(input.getAmount(), input.getPrice(), input.getDate()));
                return RESULT.OK;
            }

        } catch (Exception ex) {
            return RESULT.ERROR + ": " + ex.getMessage();
        }

        return RESULT.ERROR;
    }

    /**
     * Sale product
     *
     * @return - <code>{@link RESULT#OK}</code> or <code>{@link RESULT#ERROR}</code>
     */
    public String saleProduct(ConsoleInput input) {
        try {
            if (isAllowSale(input)) {
                addTransaction(input.getName(), new Sale(input.getAmount(), input.getPrice(), input.getDate()));
                return RESULT.OK;
            }

        } catch (Exception ex) {
            return RESULT.ERROR + ": " + ex.getMessage();
        }

        return RESULT.ERROR;
    }

    /**
     * Get sales report
     *
     * @return - <code>{@link RESULT#OK}</code> or <code>{@link RESULT#ERROR}</code>
     */
    public String salesReport(ConsoleInput input) {
        try {
            List<Transaction> transactionList = goods.get(input.getName());
            return new Report(transactionList, input).calculateProfit();

        } catch (Exception ex) {
            return RESULT.ERROR + ": " + ex.getMessage();
        }
    }

    /**
     * Add transaction in
     * oods list
     */
    private static void addTransaction(String name, Transaction transaction) {
        goods.get(name).add(transaction);
    }

    /**
     * Checking if allowed to sale
     */
    private boolean isAllowSale(ConsoleInput input) {
        boolean isAllow = false;
        // checking basic start condition
        if (isConditionCorrect(input)) {
            // get transaction list
            List<Transaction> list = goods.get(input.getName());
            // if list not empty and first element is Purchase
            // its needed because we can`t sale product if it's not purchased
            if (CollectionUtils.isNotEmpty(list) && list.get(0) instanceof Purchase) {
                isAllow = true;
            }
        }
        return isAllow;
    }

    /**
     * Checking if condition are allow purchase
     */
    private boolean isConditionCorrect(ConsoleInput input) {
        // price and amount will be null if < 0
        return isProductExists(input) && input.getPrice() != null && input.getAmount() != null;
    }

    /**
     * Check if user specified product name and it is exists in goods map
     */
    private boolean isProductExists(ConsoleInput input) {
        return goods.get(input.getName()) != null;
    }

}

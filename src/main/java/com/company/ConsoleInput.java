package com.company;
// Created by Руслан on 27.08.2019.

import com.sun.istack.internal.Nullable;
import org.apache.commons.lang3.math.NumberUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static com.company.Constants.Codes.*;

@SuppressWarnings("WeakerAccess")
public class ConsoleInput {
    private String userInput = "";
    String[] params = {};

    public String getUserInput() {
        return userInput;
    }

    public void setUserInput(String input) {
        this.userInput = input.trim();
        // the program can obtain only 5 parameters
        // so if user enter more, they will be groped at index 5th
        params = userInput.split("\\s+", 6);

    }

    /**
     * Checking what command enter user
     *
     * @param code - <code>{@link com.company.Constants.COMMAND}</code>
     * @return - boolean
     */
    public boolean is(String code) {
        return params[0].toLowerCase().startsWith(code.toLowerCase());
    }

    /**
     * @return product name
     */
    @Nullable
    public String getName() {
        if (params.length > NAME.index) {
            return params[NAME.index];
        }
        return null;
    }

    /**
     * @return - amount or 0 if not specified
     */
    @Nullable
    public Integer getAmount() {
        if (params.length > AMOUNT.index && NumberUtils.isDigits(params[AMOUNT.index])) {
            return Integer.valueOf(params[AMOUNT.index]);
        }
        return null;
    }

    /**
     * @return - price from input or null if price < 0
     */
    @Nullable
    public BigDecimal getPrice() {
        if (params.length > PRICE.index && NumberUtils.isDigits(params[PRICE.index])) { // TODO (Руслан): change the way its check digits
            BigDecimal price = new BigDecimal(params[PRICE.index]);
            return price.compareTo(BigDecimal.ZERO) > 0 ? price : null;
        }
        return null;
    }

    /**
     * @return - user specified date
     */
    @Nullable
    public LocalDate getDate() throws DateTimeParseException {
        String strDate = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        // if command was get report, than date will be at diff index
        if (is(Constants.COMMAND.SALES_REPORT) && params.length > REPORT_DATE.index) {
            strDate = params[REPORT_DATE.index];
            // get date at default index
        } else if (params.length > DATE.index) {
            strDate = params[DATE.index];
        }

        return strDate != null ? LocalDate.parse(strDate, formatter) : null;
    }

}

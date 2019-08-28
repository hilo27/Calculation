package com.company;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

// Created by Руслан on 28.08.2019.

/**
 * Don't run complete test, its design to be independent.
 */
public class ControllerTest {

    private Controller controller = new Controller();
    private ConsoleInput input = new ConsoleInput();

    @Test
    public void addProductTest() {
        input.setUserInput("NEWPRODUCT iphone");
        assertEquals(Constants.RESULT.OK, controller.addProduct(input));
        // now we must caught error
        assertEquals(Constants.RESULT.ERROR, controller.addProduct(input));
    }

    @Test
    public void purchaseProductTest() {
        // when no product was added
        input.setUserInput("PURCHASE iphone 1 1000 01.01.2017");
        assertEquals(Constants.RESULT.ERROR, controller.purchaseProduct(input));
        // add product
        add();
        // first
        input.setUserInput("PURCHASE iphone 1 1000 01.01.2017");
        assertEquals(Constants.RESULT.OK, controller.purchaseProduct(input));
        // second
        input.setUserInput("PURCHASE iphone 2 2000 01.02.2017");
        assertEquals(Constants.RESULT.OK, controller.purchaseProduct(input));
        // when input is malformed
        input.setUserInput("PURCHASE iphone -1 1000 01.01.2017");
        assertEquals(Constants.RESULT.ERROR, controller.purchaseProduct(input));
    }

    @Test
    public void saleProductTest() {
        // when no product was added
        input.setUserInput("DEMAND iphone 2 5000 01.03.2017");
        assertEquals(Constants.RESULT.ERROR, controller.saleProduct(input));
        // add product
        add();
        // make some purchase
        purchase();
        // now sale
        input.setUserInput("DEMAND iphone 2 5000 01.03.2017");
        assertEquals(Constants.RESULT.OK, controller.saleProduct(input));
        // when input is malformed
        input.setUserInput("DEMAND iphone f2 5000 01.03.2017");
        assertEquals(Constants.RESULT.ERROR, controller.saleProduct(input));
    }

    @Test
    public void salesReportTest() {
        add();
        purchase();
        sale();

        input.setUserInput("SALESREPORT iphone 02.03.2017");
        assertEquals("7000", controller.salesReport(input));
    }

    /**
     * auxiliary method
     */
    private void add() {
        input.setUserInput("NEWPRODUCT iphone");
        assertEquals(Constants.RESULT.OK, controller.addProduct(input));
    }
    /**
     * auxiliary method
     */
    private void purchase() {
        // first
        input.setUserInput("PURCHASE iphone 1 1000 01.01.2017");
        assertEquals(Constants.RESULT.OK, controller.purchaseProduct(input));
        // second
        input.setUserInput("PURCHASE iphone 2 2000 01.02.2017");
        assertEquals(Constants.RESULT.OK, controller.purchaseProduct(input));
    }
    /**
     * auxiliary method
     */
    private void sale() {
        input.setUserInput("DEMAND iphone 2 5000 01.03.2017");
        assertEquals(Constants.RESULT.OK, controller.saleProduct(input));
    }

}
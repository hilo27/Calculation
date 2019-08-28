package com.company;

import java.util.Scanner;

import static com.company.Constants.*;

public class Main {

    public static void main(String[] args) {
        Controller controller = new Controller();
        Scanner scanner = new Scanner(System.in);
        ConsoleInput input = new ConsoleInput();

        // welcome msg
        System.out.println(TEXT.WELCOME);

        while (true) {
            System.out.print("Enter command : ");

            input.setUserInput(scanner.nextLine());

            if (input.is(COMMAND.NEW_PRODUCT)) {
                System.out.println(controller.addProduct(input));

            } else if (input.is(COMMAND.PURCHASE)) {
                System.out.println(controller.purchaseProduct(input));

            } else if (input.is(COMMAND.SALE)) {
                System.out.println(controller.saleProduct(input));

            } else if (input.is(COMMAND.SALES_REPORT)) {
                System.out.println(controller.salesReport(input));

            } else if (input.is(HELP)) {
                System.out.println(TEXT.HELP);

            } else if (input.is(QUIT) || input.is("q")) {
                // EXIT
                System.out.println("--------\n");
                System.out.println("Exit");
                break;

            } else {
                System.out.println(UNKNOWN);
            }
        }

        scanner.close();
    }

}

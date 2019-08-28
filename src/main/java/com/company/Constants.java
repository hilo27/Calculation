package com.company;
// Created by Руслан on 27.08.2019.

/**
 * This is class that describe constant values that using by program
 */
public interface Constants {
    enum Codes {
        COMMAND(0),
        NAME(1),
        AMOUNT(2),
        PRICE(3),
        REPORT_DATE(2),
        DATE(4);

        public int index;

        Codes(int index) {
            this.index = index;
        }
    }

    interface TEXT {
        String WELCOME = "____    __    ____  _______  __        ______   ______   .___  ___.  _______ \n" +
                "\\   \\  /  \\  /   / |   ____||  |      /      | /  __  \\  |   \\/   | |   ____|\n" +
                " \\   \\/    \\/   /  |  |__   |  |     |  ,----'|  |  |  | |  \\  /  | |  |__   \n" +
                "  \\            /   |   __|  |  |     |  |     |  |  |  | |  |\\/|  | |   __|  \n" +
                "   \\    /\\    /    |  |____ |  `----.|  `----.|  `--'  | |  |  |  | |  |____ \n" +
                "    \\__/  \\__/     |_______||_______| \\______| \\______/  |__|  |__| |_______|\n" +
                "                                                                             ";

        String HELP = "Valid command is:\n" +
                "1) NEWPRODUCT <name> - create goods, only unique name allowed\n" +
                "2) PURCHASE <name> <amount> <price> <date>\n" +
                "3) DEMAND <name> <amount> <price> <date>\n" +
                "4) SALESREPORT <name> <date>\n" +
                "5) QUIT - if you want terminate program";

    }

    interface COMMAND {
        String NEW_PRODUCT = "NEWPRODUCT";
        String PURCHASE = "PURCHASE";
        String SALE = "DEMAND";
        String SALES_REPORT = "SALESREPORT";
    }

    interface RESULT {
        String OK = "OK";
        String ERROR = "ERROR";
    }

    String HELP = "help";
    String QUIT = "quit";
    String UNKNOWN = "Unknown command. Type HELP if you need help.";


}

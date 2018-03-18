package com.jramanta.cartCheckout.services;

import com.jramanta.cartCheckout.model.Command;
import com.jramanta.cartCheckout.model.Product;

import java.util.Map;

public class CommandService {

    /**
     * Parses the input of the application user and translates it into a valid command or throws an exception if input is invalid
     *
     * @param input the input as provided by the application user
     * @return a {@code Command}
     * @throws NoSuchMethodException
     * */
    public static Command parseCommand(String input) throws NoSuchMethodException {
        if (input != null && !input.isEmpty()) {
            for (Command commandValue : Command.values()) {
                if (input.matches(commandValue.getDescription())) {
                    return commandValue;
                }
            }
        }

        throw new NoSuchMethodException();
    }

    /**
     * Applies the command implementation
     *
     * @param command the command type
     * @param input the input as given from the application user
     * @param availableProducts a map with key all available products' code and value the products themselves
     * */
    public static void applyCommand(Command command, String input,
                                    Map<String, Product> availableProducts) throws NoSuchMethodException {

        switch (command) {
            case UPDATE:
                System.out.println("Pricing rule was applied.");
                break;
            case CHECKOUT:
                Double totalPrice = CartCheckoutService.calculateCartPrice(input, availableProducts);
                System.out.println("The total price of the cart is: " + totalPrice);
                break;
            case HELP:
                printCommandGuidelines();
                break;
            case QUIT:
                System.out.println("Exiting the application.");
                System.exit(0);
            default:
                throw new NoSuchMethodException();
        }

    }

    /**
     * The implementation of the HELP command. Prints out a list of the available valid commands of the system.
     * */
    private static void printCommandGuidelines() {
        System.out.println("- Enter pricing update command in the format: <PRODUCT> <PRICE> <NUM OF ITEMS> <OFFER PRICE>. ex. A 30 3 70");
        System.out.println("- Enter checkout command in the format of space separated product codes. ex. A B C A D");
        System.out.println("- Enter QUIT to exit the application.");
    }

}

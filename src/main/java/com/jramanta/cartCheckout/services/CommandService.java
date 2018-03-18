package com.jramanta.cartCheckout.services;

import com.jramanta.cartCheckout.model.Command;
import com.jramanta.cartCheckout.model.Product;

import java.util.Map;

public class CommandService {

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
            default:
                throw new NoSuchMethodException();
        }

    }

}

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

}

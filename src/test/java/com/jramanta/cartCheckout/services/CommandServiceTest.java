package com.jramanta.cartCheckout.services;

import com.jramanta.cartCheckout.model.Command;
import org.junit.Test;

import static org.junit.Assert.*;

public class CommandServiceTest {

    @Test(expected = NoSuchMethodException.class)
    public void testInvalidCommand() throws NoSuchMethodException{
        String input = "ABC";
        CommandService.parseCommand(input);
    }

    @Test
    public void testValidUpdateCommandWithOffer() {
        String input = "A 30.5 3 75.5";
        try {
            Command command = CommandService.parseCommand(input);
            assertEquals(Command.UPDATE, command);
        } catch (NoSuchMethodException e) {
            fail();
        }
    }

    @Test
    public void testValidUpdateCommandWithoutOffer() {
        String input = "A 30.5";
        try {
            Command command = CommandService.parseCommand(input);
            assertEquals(Command.UPDATE, command);
        } catch (NoSuchMethodException e) {
            fail();
        }

    }

    @Test
    public void testValidCheckoutCommand() {
        String input = "A B C";
        try {
            Command command = CommandService.parseCommand(input);
            assertEquals(Command.CHECKOUT, command);
        } catch (NoSuchMethodException e) {
            fail();
        }

    }

}
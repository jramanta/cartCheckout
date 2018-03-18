package com.jramanta.cartCheckout.services;

import com.jramanta.cartCheckout.TestUtil;
import com.jramanta.cartCheckout.model.Command;
import com.jramanta.cartCheckout.model.Product;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static com.jramanta.cartCheckout.TestUtil.DELTA;
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

    @Test
    public void testValidHelpCommand() {
        String input = "HELP";
        try {
            Command command = CommandService.parseCommand(input);
            assertEquals(Command.HELP, command);
        } catch (NoSuchMethodException e) {
            fail();
        }

    }

    @Test
    public void testValidQuitCommand() {
        String input = "QUIT";
        try {
            Command command = CommandService.parseCommand(input);
            assertEquals(Command.QUIT, command);
        } catch (NoSuchMethodException e) {
            fail();
        }

    }

    @Test
    public void testApplyValidUpdateCommandWithOffer() throws NoSuchMethodException {
        Command command = Command.UPDATE;
        String commandInput = "Z 30 3 100";
        Map<String, Product> availableProducts = new HashMap<>();
        Product aProduct = TestUtil.initProduct("Z", 40);
        availableProducts.put("Z", aProduct);

        CommandService.applyCommand(command, commandInput, availableProducts);

        assertEquals(aProduct.getCode(), "Z");
        assertEquals(aProduct.getUnitPrice(), 30, DELTA);
        assertNotNull(aProduct.getWeeklyOffer());
        assertEquals(aProduct.getWeeklyOffer().getPrice(), 100, DELTA);
        assertEquals(aProduct.getWeeklyOffer().getNumberOfItems(), 3);
    }

    @Test
    public void testApplyValidUpdateCommandNoOffer() throws NoSuchMethodException {
        Command command = Command.UPDATE;
        String commandInput = "Z 30";
        Map<String, Product> availableProducts = new HashMap<>();
        Product aProduct = TestUtil.productWithOffer("Z", 40, 4, 150);
        availableProducts.put("Z", aProduct);

        CommandService.applyCommand(command, commandInput, availableProducts);

        assertEquals(aProduct.getCode(), "Z");
        assertEquals(aProduct.getUnitPrice(), 30, DELTA);
        assertNull(aProduct.getWeeklyOffer());
    }

    @Test(expected = NoSuchMethodException.class)
    public void testApplyInvalidUpdateCommand() throws NoSuchMethodException {
        Command command = Command.UPDATE;
        String commandInput = "Z 30 3 45 22";
        Map<String, Product> availableProducts = new HashMap<>();
        Product aProduct = TestUtil.productWithOffer("Z", 40, 4, 150);
        availableProducts.put("Z", aProduct);

        CommandService.applyCommand(command, commandInput, availableProducts);
    }

}
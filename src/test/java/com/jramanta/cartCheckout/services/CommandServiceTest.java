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
    public void testInvalidCommand() throws NoSuchMethodException {
        String input = "ABC";
        CommandService.parseCommand(input);
    }

    @Test
    public void testValidUpdateCommandWithOffer() throws NoSuchMethodException {
        String input = "A 30.5 3 75.5";
        Command command = CommandService.parseCommand(input);
        assertEquals(Command.UPDATE, command);
    }

    @Test
    public void testValidUpdateCommandWithoutOffer() throws NoSuchMethodException {
        String input = "A 30.5";
        Command command = CommandService.parseCommand(input);
        assertEquals(Command.UPDATE, command);
    }

    @Test(expected = NoSuchMethodException.class)
    public void testInvalidUpdateCommandWithoutOffer() throws NoSuchMethodException {
        String input = "A 30.5 77";
        CommandService.parseCommand(input);
    }

    @Test
    public void testValidCheckoutCommand() throws NoSuchMethodException {
        String input = "A B C";
        Command command = CommandService.parseCommand(input);
        assertEquals(Command.CHECKOUT, command);
    }

    @Test(expected = NoSuchMethodException.class)
    public void testInvalidCheckoutCommand() throws NoSuchMethodException {
        String input = "A BB C";
        CommandService.parseCommand(input);
    }

    @Test
    public void testValidHelpCommand() throws NoSuchMethodException {
        String input = "HELP";
        Command command = CommandService.parseCommand(input);
        assertEquals(Command.HELP, command);
    }

    @Test(expected = NoSuchMethodException.class)
    public void testInvalidHelpCommand() throws NoSuchMethodException {
        String input = "help";
        CommandService.parseCommand(input);
    }

    @Test
    public void testValidQuitCommand() throws NoSuchMethodException {
        String input = "QUIT";
        Command command = CommandService.parseCommand(input);
        assertEquals(Command.QUIT, command);
    }

    @Test(expected = NoSuchMethodException.class)
    public void testInvalidQuitCommand() throws NoSuchMethodException {
        String input = "quit";
        Command command = CommandService.parseCommand(input);
        assertEquals(Command.QUIT, command);
    }

    @Test
    public void testValidProductsCommand() throws NoSuchMethodException {
        String input = "PRODUCTS";
        Command command = CommandService.parseCommand(input);
        assertEquals(Command.PRODUCT_LIST, command);
    }

    @Test(expected = NoSuchMethodException.class)
    public void testInvalidProductsCommand() throws NoSuchMethodException {
        String input = "products";
        CommandService.parseCommand(input);
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

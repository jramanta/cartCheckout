package com.jramanta.cartCheckout.model;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class CommandTest {

    @Test
    public void testInvalidUPDATECommand() {
        Set<String> testInvalidCommand = new HashSet<>();
        testInvalidCommand.add("AB 2 3 4 5");
        testInvalidCommand.add("A");
        testInvalidCommand.add("A 2 3");
        testInvalidCommand.add("A 2 3.1 3");
        testInvalidCommand.add("2 3 3 4");
        testInvalidCommand.add("2");
        testInvalidCommand.add("# 3 3 4");
        testInvalidCommand.add("W 13.1 13.2 13.2");


        for(String input : testInvalidCommand) {
            assertFalse(input.matches(Command.UPDATE.getDescription()));
        }
    }

    @Test
    public void testValidUPDATECommand() {
        Set<String> testValidCommand = new HashSet<>();
        testValidCommand.add("C 2 3 4");
        testValidCommand.add("C 1.2 12 100.9");
        testValidCommand.add("E 2000000");
        testValidCommand.add("A 3000000.1111");
        testValidCommand.add("Z 200000.11 123456789 120000000.22");
        testValidCommand.add("Q 0.1");
        testValidCommand.add("K 0.1 5 0.2");

        for(String input : testValidCommand) {
            assertTrue(input.matches(Command.UPDATE.getDescription()));
        }
    }

    @Test
    public void testInvalidCHECKOUTCommand() {
        Set<String> testInvalidCommand = new HashSet<>();
        testInvalidCommand.add("A 2 3 4");
        testInvalidCommand.add("A 2");
        testInvalidCommand.add("1 2 3");
        testInvalidCommand.add("AB");
        testInvalidCommand.add("A AB");
        testInvalidCommand.add("A B ");
        testInvalidCommand.add(" A ");
        testInvalidCommand.add("1 H");
        testInvalidCommand.add("1 X Y");


        for(String input : testInvalidCommand) {
            assertFalse(input.matches(Command.CHECKOUT.getDescription()));
        }
    }

    @Test
    public void testValidCHECKOUTCommand() {
        Set<String> testValidCommand = new HashSet<>();
        testValidCommand.add("A");
        testValidCommand.add("Z");
        testValidCommand.add("A B");
        testValidCommand.add("A B C D E F G");
        testValidCommand.add("Z Z Z Z Z Z");
        testValidCommand.add("A B A B A B A B");
        testValidCommand.add("G H H H H H G G G G");

        for(String input : testValidCommand) {
            assertTrue(input.matches(Command.CHECKOUT.getDescription()));
        }
    }

    @Test
    public void testValidHELPCommand() {
       String input = "HELP";

       assertTrue(input.matches(Command.HELP.getDescription()));

    }

    @Test
    public void testInvalidHELPCommand() {
        String input = "help";

        assertFalse(input.matches(Command.HELP.getDescription()));

    }

    @Test
    public void testValidQUITCommand() {
        String input = "QUIT";

        assertTrue(input.matches(Command.QUIT.getDescription()));

    }

    @Test
    public void testInvalidQUITCommand() {
        String input = "quit";

        assertFalse(input.matches(Command.QUIT.getDescription()));

    }

}
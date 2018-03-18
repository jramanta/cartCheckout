package com.jramanta.cartCheckout.services;

import com.jramanta.cartCheckout.model.Command;
import com.jramanta.cartCheckout.model.Product;

import java.io.InputStream;
import java.util.Map;
import java.util.Scanner;

public class ScannerService {

    /**
     * The method for scanning, processing and applying user input commands.
     * */
    public static void processInput(Map<String, Product> availableProducts) {
        while (true) {
            System.out.println("Enter command or type HELP: ");
            try {
                Scanner sc = new Scanner(System.in);
                if (sc.hasNext()) {
                    String input = sc.nextLine();
                    Command command = CommandService.parseCommand(input);
                    CommandService.applyCommand(command, input, availableProducts);
                }
            } catch (NoSuchMethodException e) {
                System.out.println("Invalid input command.");
            }
        }
    }

    /**
     * Scanning of the given file path and returns the contents in the format of a string with each line separated by the new line character.
     *
     * @param fileName the file path name
     * @return a string representation of the file's contents
     * */
    public static String scanFileInput(String fileName) {

        StringBuilder result = new StringBuilder("");
        InputStream input = ScannerService.class.getResourceAsStream(fileName);

        Scanner scanner = new Scanner(input);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            result.append(line).append("\n");
        }

        scanner.close();
        return result.toString();

    }
}

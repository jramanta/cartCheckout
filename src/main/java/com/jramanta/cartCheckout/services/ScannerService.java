package com.jramanta.cartCheckout.services;

import com.jramanta.cartCheckout.model.Command;
import com.jramanta.cartCheckout.model.Product;

import java.io.InputStream;
import java.util.Map;
import java.util.Scanner;

public class ScannerService {

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

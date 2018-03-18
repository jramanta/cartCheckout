package com.jramanta.cartCheckout;

import com.jramanta.cartCheckout.model.Product;
import com.jramanta.cartCheckout.services.InitializeService;
import com.jramanta.cartCheckout.services.ScannerService;

import java.util.Map;

public class CartCheckoutApplication {

    private static final String INITIALIZE_FILE_PATH = "/initialization";

    public static void main(String args[]) {
        Map<String, Product> availableProducts = InitializeService
                .initializeProductMap(INITIALIZE_FILE_PATH);
        ScannerService.processInput(availableProducts);
    }
}

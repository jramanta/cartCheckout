package com.jramanta.cartCheckout.services;

import com.jramanta.cartCheckout.model.Product;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InitializeService {

    private static final String PRODUCT_PATTERN = "^[A-Z] ";

    /**
     * Parse the contents of the file and build the list of initial products with their initial pricing.
     *
     * @param filePath the relative path of the initialization file
     * */
    public static Map<String, Product> initializeProductMap(String filePath) {

        Map<String, Product> availableProducts = new HashMap<>();

        try {
            String initPricing = ScannerService.scanFileInput(filePath);
            availableProducts = initializeProductPricing(initPricing);
        } catch (Exception e) {
            System.out.println("Fatal Error Initializing the application. Exiting.");
            System.exit(0);
        }

        return availableProducts;
    }

    public static Map<String, Product> initializeProductPricing(String initPricing)
            throws NoSuchMethodException {
        Map<String, Product> availableProducts = new HashMap<>();
        String[] pricingRules = initPricing.split("\n");

        for (String rule : pricingRules) {
            initializeProduct(rule, availableProducts);
            PricingService.applyPricingRule(rule, availableProducts);
        }

        return availableProducts;
    }

    private static void initializeProduct(String initializeRule, Map<String, Product> availableProducts) {
        Pattern pattern = Pattern.compile(PRODUCT_PATTERN);
        Matcher matcher = pattern.matcher(initializeRule);
        if (matcher.find()) {
            String productCode = matcher.group(0).trim();
            availableProducts.put(productCode, new Product(productCode));
        } else {
            throw new IllegalArgumentException();
        }
    }
}

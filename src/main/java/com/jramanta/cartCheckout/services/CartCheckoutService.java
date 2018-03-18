package com.jramanta.cartCheckout.services;

import com.jramanta.cartCheckout.model.Product;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.stream.Collectors;

public class CartCheckoutService {

    /**
     * Calculates the total price of the given cart given the prices and offers of the available products.
     *
     * @param cartInput an input String in the format of products separated by a space character.
     * @param availableProducts a map of all available products with key the products' code.
     * */
    public static Double calculateCartPrice(String cartInput, Map<String, Product> availableProducts) {
        Map<String, Long> cart = buildUserCart(cartInput);

        return cart.entrySet()
                .stream()
                .mapToDouble(calculatePrice(availableProducts)).sum();
    }

    /**
     * Function implementation for product price calculation.
     * */
    private static ToDoubleFunction<Map.Entry<String, Long>> calculatePrice(Map<String, Product> availableProducts) {
        return entry -> {
            Double price = 0d;
            Product aProduct = availableProducts.get(entry.getKey());
            if (aProduct != null) {
                price = calculateProductPrice(aProduct, entry.getValue());
            }
            return price;
        };
    }

    /**
     * A map with key the product code and value the number of the product occurrences in the cart is built.
     *
     * @param cartInput our cart for checkout in the form of product codes separated by a space.
     * */
    private static Map<String, Long> buildUserCart(String cartInput) {
        List<String> productCodes = Arrays.asList(cartInput.split(" "));
        return productCodes
                .stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    /**
     * The calculation of a product price based on the total product's occurrences.
     * If the product has an offer we divide the product count by the offers number of items to find how many product's offers apply.
     * The remainder of the division is the number of items that don't apply on the offer so the unit item price applies
     * If the product has no offer then the product count is multiplied by the product unit's price.
     *
     * @param product one of the available products
     * @param productCount the count of the product in the cart
     * @return the total price for the input product after the offer has been applied
     * */
    private static Double calculateProductPrice(Product product, long productCount) {
        Double productTotalPrice = 0d;

        if (product.getWeeklyOffer() != null) {
            long offerItems = product.getWeeklyOffer().getNumberOfItems();
            // find numbers of offers
            long timesOfOffer = productCount / offerItems;
            if (timesOfOffer > 0) {
                // multiply the offer price by the offer count
                productTotalPrice = timesOfOffer * product.getWeeklyOffer().getPrice();
            }

            // calculate the remainder of the above division
            long productUnitRemainder = productCount % offerItems;
            if (productUnitRemainder > 0) {
                // add to the total price the multiplication of the remainder with the product unit price
                productTotalPrice = productTotalPrice + productUnitRemainder * product.getUnitPrice();
            }
        } else {
            // if no offer applies multiply the product unit price with the product count
            productTotalPrice = product.getUnitPrice() * productCount;
        }

        return productTotalPrice;
    }

}

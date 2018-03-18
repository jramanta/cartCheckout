package com.jramanta.cartCheckout.services;

import com.jramanta.cartCheckout.model.Product;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.stream.Collectors;

public class CartCheckoutService {

    public static Double calculateCartPrice(String cartInput, Map<String, Product> availableProducts) {
        Map<String, Long> cart = buildUserCart(cartInput);

        return cart.entrySet()
                .stream()
                .mapToDouble(calculatePrice(availableProducts)).sum();
    }

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

    private static Map<String, Long> buildUserCart(String cartInput) {
        List<String> productCodes = Arrays.asList(cartInput.split(" "));
        return productCodes
                .stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    private static Double calculateProductPrice(Product product, long productCount) {
        Double productTotalPrice = 0d;

        if (product.getWeeklyOffer() != null) {
            long offerItems = product.getWeeklyOffer().getNumberOfItems();
            long timesOfOffer = productCount / (product.getWeeklyOffer().getNumberOfItems());
            if (timesOfOffer > 0) {
                productTotalPrice = timesOfOffer * product.getWeeklyOffer().getPrice();
            }

            long productUnitRemainder = productCount % offerItems;
            if (productUnitRemainder > 0) {
                productTotalPrice = productTotalPrice + productUnitRemainder * product.getUnitPrice();
            }
        } else {
            productTotalPrice = product.getUnitPrice() * productCount;
        }
        return productTotalPrice;
    }

}

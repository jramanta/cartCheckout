package com.jramanta.cartCheckout;

import com.jramanta.cartCheckout.model.Product;
import com.jramanta.cartCheckout.model.WeeklyOffer;

public class TestUtil {

    public static final double DELTA = 1e-10;

    public static Product initProduct(String code, double price) {
        Product aProduct = new Product(code);
        aProduct.setUnitPrice(price);
        return aProduct;
    }

    private static WeeklyOffer initOffer(long numOfItems, double offerPrice) {
        return new WeeklyOffer(numOfItems, offerPrice);
    }

    public static Product productWithOffer(String code, double price, long numOfItems, double offerPrice) {
        Product product = initProduct(code, price);
        WeeklyOffer offer = initOffer(numOfItems, offerPrice);
        product.setWeeklyOffer(offer);

        return product;
    }
}

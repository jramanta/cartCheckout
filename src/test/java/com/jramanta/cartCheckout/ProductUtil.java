package com.jramanta.cartCheckout;

import com.jramanta.cartCheckout.model.Product;
import com.jramanta.cartCheckout.model.WeeklyOffer;

public class ProductUtil {

    public static Product initProduct(String code, double price) {
        Product aProduct = new Product(code);
        aProduct.setUnitPrice(price);
        return aProduct;
    }

    public static WeeklyOffer initOffer(long numOfItems, double offerPrice) {
        WeeklyOffer offer = new WeeklyOffer(numOfItems, offerPrice);
        return offer;
    }

    public static Product productWithOffer(String code, double price, long numOfItems, double offerPrice) {
        Product product = initProduct(code, price);
        WeeklyOffer offer = initOffer(numOfItems, offerPrice);
        product.setWeeklyOffer(offer);

        return product;
    }
}

package com.jramanta.cartCheckout.services;

import com.jramanta.cartCheckout.ProductUtil;
import com.jramanta.cartCheckout.model.Product;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class CartCheckoutServiceTest {

    private static final double DELTA = 1e-10;

    private Map<String, Product> availableProducts;

    @Before
    public void setUp() {
        availableProducts = new HashMap<>();
    }

    // SINGLE PRODUCT CART TESTS

    @Test
    public void calculateSingleProductWithoutOffer() {

        String cartInput = "A A A A A A A";

        Product productA = ProductUtil.initProduct("A", 30);
        availableProducts.put("A", productA);

        double price = CartCheckoutService.calculateCartPrice(cartInput, availableProducts);

        assertEquals(price, 210, DELTA);
    }

    @Test
    public void calculateSingleProductWithOfferAndUnitPrice() {

        String cartInput = "A A A A A A A";

        Product productA = ProductUtil.productWithOffer("A", 30, 3, 70);
        availableProducts.put("A", productA);

        double price = CartCheckoutService.calculateCartPrice(cartInput, availableProducts);

        assertEquals(price, 170.0, DELTA);
    }

    @Test
    public void calculateSingleProductWithOfferOnly() {

        String cartInput = "A A A A A A";

        Product productA = ProductUtil.productWithOffer("A", 30, 3, 70);
        availableProducts.put("A", productA);

        double price = CartCheckoutService.calculateCartPrice(cartInput, availableProducts);

        assertEquals(price, 140.0, DELTA);
    }

    @Test
    public void calculateSingleProductWithOfferNotApplied() {

        String cartInput = "A A A A";

        Product productA = ProductUtil.productWithOffer("A", 30, 5, 100);
        availableProducts.put("A", productA);

        double price = CartCheckoutService.calculateCartPrice(cartInput, availableProducts);

        assertEquals(price, 120.0, DELTA);
    }

    // SINGLE PRODUCT CART TESTS WITH DECIMAL PRICES

    @Test
    public void calculateSingleProductDecimalPriceWithoutOffer() {

        String cartInput = "A A A A A A A";

        Product productA = ProductUtil.initProduct("A", 30.8);
        availableProducts.put("A", productA);

        double price = CartCheckoutService.calculateCartPrice(cartInput, availableProducts);

        assertEquals(price, 215.6, DELTA);
    }

    @Test
    public void calculateSingleProductDecimalPriceWithOfferAndUnitPrice() {

        String cartInput = "A A A A A A A";

        Product productA = ProductUtil.productWithOffer("A", 30.5, 3, 70.4);
        availableProducts.put("A", productA);

        double price = CartCheckoutService.calculateCartPrice(cartInput, availableProducts);

        assertEquals(price, 171.3, DELTA);
    }

    @Test
    public void calculateSingleProductDecimalPriceWithOfferOnly() {

        String cartInput = "A A A A A A";

        Product productA = ProductUtil.productWithOffer("A", 30.5, 3, 70.4);
        availableProducts.put("A", productA);

        double price = CartCheckoutService.calculateCartPrice(cartInput, availableProducts);

        assertEquals(price, 140.8, DELTA);
    }

    // MULTIPLE PRODUCT CART TESTS

    @Test
    public void calculateMultipleProductWithoutOffer() {

        String cartInput = "A B C D E";

        Product productA = ProductUtil.initProduct("A", 30);
        Product productB = ProductUtil.initProduct("B", 15.2);
        Product productC = ProductUtil.initProduct("C", 100.4);
        Product productD = ProductUtil.initProduct("D", 5);
        Product productE = ProductUtil.initProduct("E", 0.1);

        availableProducts.put("A", productA);
        availableProducts.put("B", productB);
        availableProducts.put("C", productC);
        availableProducts.put("D", productD);
        availableProducts.put("E", productE);

        double price = CartCheckoutService.calculateCartPrice(cartInput, availableProducts);

        assertEquals(price, 150.7, DELTA);
    }

    @Test
    public void calculateMultipleProductWithOffer() {

        String cartInput = "A B C D E";

        Product productA = ProductUtil.productWithOffer("A", 30, 4, 100);
        Product productB = ProductUtil.productWithOffer("B", 15.2, 2, 25.5);
        Product productC = ProductUtil.productWithOffer("C", 100.4, 3, 245.6);
        Product productD = ProductUtil.productWithOffer("D", 5, 3, 13.2);
        Product productE = ProductUtil.initProduct("E", 0.1);

        availableProducts.put("A", productA);
        availableProducts.put("B", productB);
        availableProducts.put("C", productC);
        availableProducts.put("D", productD);
        availableProducts.put("E", productE);

        double price = CartCheckoutService.calculateCartPrice(cartInput, availableProducts);

        assertEquals(price, 150.7, DELTA);
    }

    @Test
    public void calculateMultipleProductCombinationsWithoutOffer() {

        String cartInput = "D A B E A A C D E C B B D A B B D E";

        Product productA = ProductUtil.initProduct("A", 30);
        Product productB = ProductUtil.initProduct("B", 15.2);
        Product productC = ProductUtil.initProduct("C", 100.4);
        Product productD = ProductUtil.initProduct("D", 5);
        Product productE = ProductUtil.initProduct("E", 0.1);

        availableProducts.put("A", productA);
        availableProducts.put("B", productB);
        availableProducts.put("C", productC);
        availableProducts.put("D", productD);
        availableProducts.put("E", productE);

        double price = CartCheckoutService.calculateCartPrice(cartInput, availableProducts);

        assertEquals(price, 417.1, DELTA);
    }

    @Test
    public void calculateMultipleProductCombinationsWithOffer() {

        String cartInput = "D A B E A A C D E C B B D A B B D E";

        Product productA = ProductUtil.productWithOffer("A", 30, 4, 100);
        Product productB = ProductUtil.productWithOffer("B", 15.2, 2, 25.5);
        Product productC = ProductUtil.productWithOffer("C", 100.4, 3, 245.6);
        Product productD = ProductUtil.productWithOffer("D", 5, 3, 13.2);
        Product productE = ProductUtil.initProduct("E", 0.1);

        availableProducts.put("A", productA);
        availableProducts.put("B", productB);
        availableProducts.put("C", productC);
        availableProducts.put("D", productD);
        availableProducts.put("E", productE);

        double price = CartCheckoutService.calculateCartPrice(cartInput, availableProducts);

        assertEquals(price, 385.5, DELTA);
    }
}
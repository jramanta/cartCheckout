package com.jramanta.cartCheckout.services;

import com.jramanta.cartCheckout.TestUtil;
import com.jramanta.cartCheckout.model.Product;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static com.jramanta.cartCheckout.TestUtil.DELTA;
import static org.junit.Assert.*;

public class PricingServiceTest {

    private Map<String, Product> availableProducts;

    @Before
    public void setUp() {
        availableProducts = new HashMap<>();
        Product productA = TestUtil.initProduct("A", 20.5);
        Product productB = TestUtil.initProduct("B", 3000);
        Product productC = TestUtil.productWithOffer("C", 40, 5, 150);
        Product productD = TestUtil.productWithOffer("D", 70.55, 10, 170.55);

        availableProducts.put("A", productA);
        availableProducts.put("B", productB);
        availableProducts.put("C", productC);
        availableProducts.put("D", productD);
    }

    @Test(expected = NoSuchMethodException.class)
    public void testInvalidRuleInput() throws NoSuchMethodException {
        String inputY = "A 40 10 10 10";

        PricingService.applyPricingRule(inputY, availableProducts);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidProductInRule() throws NoSuchMethodException {
        String inputY = "Y 40";

        PricingService.applyPricingRule(inputY, availableProducts);
    }

    @Test
    public void testInputWithoutOffer() {
        String inputA = "A 60";
        String inputB = "B 60";
        String inputC = "C 60";
        String inputD = "D 60";

        try {
            PricingService.applyPricingRule(inputA, availableProducts);
            PricingService.applyPricingRule(inputB, availableProducts);
            PricingService.applyPricingRule(inputC, availableProducts);
            PricingService.applyPricingRule(inputD, availableProducts);

            Product product = availableProducts.get("A");
            assertEquals(product.getUnitPrice(), 60, DELTA);
            assertNull(product.getWeeklyOffer());
            product = availableProducts.get("B");
            assertEquals(product.getUnitPrice(), 60, DELTA);
            assertNull(product.getWeeklyOffer());
            product = availableProducts.get("C");
            assertEquals(product.getUnitPrice(), 60, DELTA);
            assertNull(product.getWeeklyOffer());
            product = availableProducts.get("D");
            assertEquals(product.getUnitPrice(), 60, DELTA);
            assertNull(product.getWeeklyOffer());
        } catch (NoSuchMethodException e) {
            fail();
        }
    }

    @Test
    public void testInputWithoutOfferDecimalPrice() {
        String inputA = "A 100.5";
        String inputB = "B 100.5";
        String inputC = "C 100.5";
        String inputD = "D 100.5";

        try {
            PricingService.applyPricingRule(inputA, availableProducts);
            PricingService.applyPricingRule(inputB, availableProducts);
            PricingService.applyPricingRule(inputC, availableProducts);
            PricingService.applyPricingRule(inputD, availableProducts);

            Product product = availableProducts.get("A");
            assertEquals(product.getUnitPrice(), 100.5, DELTA);
            assertNull(product.getWeeklyOffer());
            product = availableProducts.get("B");
            assertEquals(product.getUnitPrice(), 100.5, DELTA);
            assertNull(product.getWeeklyOffer());
            product = availableProducts.get("C");
            assertEquals(product.getUnitPrice(), 100.5, DELTA);
            assertNull(product.getWeeklyOffer());
            product = availableProducts.get("D");
            assertEquals(product.getUnitPrice(), 100.5, DELTA);
            assertNull(product.getWeeklyOffer());
        } catch (NoSuchMethodException e) {
            fail();
        }
    }

    @Test
    public void testInputWithOffer() {
        String inputA = "A 60 4 120";
        String inputB = "B 60 3 140";
        String inputC = "C 60 2 300";
        String inputD = "D 60 60 1200";

        try {
            PricingService.applyPricingRule(inputA, availableProducts);
            PricingService.applyPricingRule(inputB, availableProducts);
            PricingService.applyPricingRule(inputC, availableProducts);
            PricingService.applyPricingRule(inputD, availableProducts);

            Product product = availableProducts.get("A");
            assertEquals(product.getUnitPrice(), 60, DELTA);
            assertEquals(product.getWeeklyOffer().getNumberOfItems(), 4);
            assertEquals(product.getWeeklyOffer().getPrice(), 120, DELTA);
            product = availableProducts.get("B");
            assertEquals(product.getUnitPrice(), 60, DELTA);
            assertEquals(product.getWeeklyOffer().getNumberOfItems(), 3);
            assertEquals(product.getWeeklyOffer().getPrice(), 140, DELTA);
            product = availableProducts.get("C");
            assertEquals(product.getUnitPrice(), 60, DELTA);
            assertEquals(product.getWeeklyOffer().getNumberOfItems(), 2);
            assertEquals(product.getWeeklyOffer().getPrice(), 300, DELTA);
            product = availableProducts.get("D");
            assertEquals(product.getUnitPrice(), 60, DELTA);
            assertEquals(product.getWeeklyOffer().getNumberOfItems(), 60);
            assertEquals(product.getWeeklyOffer().getPrice(), 1200, DELTA);
        } catch (NoSuchMethodException e) {
            fail();
        }
    }

    @Test
    public void testInputWithOfferDecimalPrices() {
        String inputA = "A 43.6 4 120.1222";
        String inputB = "B 111.1 3 140.33";
        String inputC = "C 6.5 2 300.4";
        String inputD = "D 0.1 60 1200.65";

        try {
            PricingService.applyPricingRule(inputA, availableProducts);
            PricingService.applyPricingRule(inputB, availableProducts);
            PricingService.applyPricingRule(inputC, availableProducts);
            PricingService.applyPricingRule(inputD, availableProducts);

            Product product = availableProducts.get("A");
            assertEquals(product.getUnitPrice(), 43.6, DELTA);
            assertEquals(product.getWeeklyOffer().getNumberOfItems(), 4);
            assertEquals(product.getWeeklyOffer().getPrice(), 120.1222, DELTA);
            product = availableProducts.get("B");
            assertEquals(product.getUnitPrice(), 111.1, DELTA);
            assertEquals(product.getWeeklyOffer().getNumberOfItems(), 3);
            assertEquals(product.getWeeklyOffer().getPrice(), 140.33, DELTA);
            product = availableProducts.get("C");
            assertEquals(product.getUnitPrice(), 6.5, DELTA);
            assertEquals(product.getWeeklyOffer().getNumberOfItems(), 2);
            assertEquals(product.getWeeklyOffer().getPrice(), 300.4, DELTA);
            product = availableProducts.get("D");
            assertEquals(product.getUnitPrice(), 0.1, DELTA);
            assertEquals(product.getWeeklyOffer().getNumberOfItems(), 60);
            assertEquals(product.getWeeklyOffer().getPrice(), 1200.65, DELTA);
        } catch (NoSuchMethodException e) {
            fail();
        }
    }

    @Test
    public void testSameInputUpdate() {
        String inputA1 = "A 60 4 120";
        String inputA2 = "A 12.555";
        String inputA3 = "A 30.5 2 300.43";
        String inputA4 = "A 0.1 60 1200";
        String inputA5 = "A 14 5 35.1";

        try {
            PricingService.applyPricingRule(inputA1, availableProducts);
            PricingService.applyPricingRule(inputA2, availableProducts);
            PricingService.applyPricingRule(inputA3, availableProducts);
            PricingService.applyPricingRule(inputA4, availableProducts);
            PricingService.applyPricingRule(inputA5, availableProducts);

            Product product = availableProducts.get("A");
            assertEquals(product.getUnitPrice(), 14, DELTA);
            assertEquals(product.getWeeklyOffer().getNumberOfItems(), 5);
            assertEquals(product.getWeeklyOffer().getPrice(), 35.1, DELTA);
        } catch (NoSuchMethodException e) {
            fail();
        }
    }

    @Test(expected = NoSuchMethodException.class)
    public void testZeroOfferPriceInputUpdate() throws NoSuchMethodException {
        String input = "A 60 4 0";

        PricingService.applyPricingRule(input, availableProducts);

    }

    @Test(expected = NoSuchMethodException.class)
    public void testEqualToOneOfferCountInputUpdate() throws NoSuchMethodException {
        String input = "A 60 1 40";

        PricingService.applyPricingRule(input, availableProducts);

    }

    @Test(expected = NoSuchMethodException.class)
    public void testNegativeOfferPriceInputUpdate() throws NoSuchMethodException {
        String input = "A 60 -2 40";

        PricingService.applyPricingRule(input, availableProducts);

    }

    @Test(expected = NoSuchMethodException.class)
    public void testNegativeProductPriceInputUpdate() throws NoSuchMethodException {
        String input = "A -60 2 40";

        PricingService.applyPricingRule(input, availableProducts);

    }

    @Test(expected = NoSuchMethodException.class)
    public void testZeroProductPriceInputUpdate() throws NoSuchMethodException {
        String input = "A 0 2 40";

        PricingService.applyPricingRule(input, availableProducts);

    }
}

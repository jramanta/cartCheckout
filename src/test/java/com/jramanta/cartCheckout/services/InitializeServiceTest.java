package com.jramanta.cartCheckout.services;

import com.jramanta.cartCheckout.model.Product;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

public class InitializeServiceTest {

    private static final double DELTA = 1e-10;

    @Before
    public void setUp() {
    }

    @Test
    public void checkValidInitialization() throws NoSuchMethodException {
        String processedFileInput = "A 50 3 130\nB 20.3 2 19.5\nC 20\nD 100.4";

        Map<String, Product> products = InitializeService.initializeProductPricing(processedFileInput);
        assertEquals(products.size(), 4);

        assertTrue(products.containsKey("A"));
        Product productA = products.get("A");
        assertEquals(productA.getUnitPrice(), 50, DELTA);
        assertEquals(productA.getCode(), "A");
        assertEquals(productA.getWeeklyOffer().getNumberOfItems(), 3);
        assertEquals(productA.getWeeklyOffer().getPrice(), 130, DELTA);

        assertTrue(products.containsKey("B"));
        Product productB = products.get("B");
        assertEquals(productB.getUnitPrice(), 20.3, DELTA);
        assertEquals(productB.getCode(), "B");
        assertEquals(productB.getWeeklyOffer().getNumberOfItems(), 2);
        assertEquals(productB.getWeeklyOffer().getPrice(), 19.5, DELTA);

        assertTrue(products.containsKey("C"));
        Product productC = products.get("C");
        assertEquals(productC.getUnitPrice(), 20, DELTA);
        assertEquals(productC.getCode(), "C");
        assertNull(productC.getWeeklyOffer());

        assertTrue(products.containsKey("D"));
        Product productD = products.get("D");
        assertEquals(productD.getUnitPrice(), 100.4, DELTA);
        assertEquals(productD.getCode(), "D");
        assertNull(productD.getWeeklyOffer());
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkInvalidCommandInitialization() throws NoSuchMethodException {
        String processedFileInput = "AB 50 3 130";

        InitializeService.initializeProductPricing(processedFileInput);
    }

    @Test(expected = NoSuchMethodException.class)
    public void checkInvalidPricingInitialization() throws NoSuchMethodException {
        String processedFileInput = "A 50 3 130 4";

        InitializeService.initializeProductPricing(processedFileInput);
    }

}
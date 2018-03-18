package com.jramanta.cartCheckout.model;

import java.util.Objects;

public class Product {

    private final String code;
    private double unitPrice;
    private WeeklyOffer weeklyOffer;

    public Product(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public WeeklyOffer getWeeklyOffer() {
        return weeklyOffer;
    }

    public void setWeeklyOffer(WeeklyOffer weeklyOffer) {
        this.weeklyOffer = weeklyOffer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(product.unitPrice, unitPrice) == 0 &&
                Objects.equals(code, product.code) &&
                Objects.equals(weeklyOffer, product.weeklyOffer);
    }

    @Override
    public int hashCode() {

        return Objects.hash(code, unitPrice, weeklyOffer);
    }
}

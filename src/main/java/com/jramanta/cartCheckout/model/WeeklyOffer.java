package com.jramanta.cartCheckout.model;

import java.util.Objects;

public class WeeklyOffer {

    private final long numberOfItems;
    private final double price;

    public WeeklyOffer(long numberOfItems, double price) {
        this.numberOfItems = numberOfItems;
        this.price = price;
    }

    public long getNumberOfItems() {
        return numberOfItems;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeeklyOffer that = (WeeklyOffer) o;
        return numberOfItems == that.numberOfItems &&
                Double.compare(that.price, price) == 0;
    }

    @Override
    public int hashCode() {

        return Objects.hash(numberOfItems, price);
    }
}

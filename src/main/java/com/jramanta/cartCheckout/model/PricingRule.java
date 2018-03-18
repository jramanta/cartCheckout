package com.jramanta.cartCheckout.model;

public class PricingRule {

    private final String productCode;
    private final Double productPrice;
    private Integer numOfItems;
    private Double offerPricing;

    public PricingRule(String productCode, Double productPrice, Integer numOfItems, Double offerPricing) {
        this.productCode = productCode;
        this.productPrice = productPrice;
        this.numOfItems = numOfItems;
        this.offerPricing = offerPricing;
    }

    public PricingRule(String productCode, Double productPrice) {
        this.productCode = productCode;
        this.productPrice = productPrice;
    }

    public String getProductCode() {
        return productCode;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public Integer getNumOfItems() {
        return numOfItems;
    }

    public Double getOfferPricing() {
        return offerPricing;
    }

}

package com.jramanta.cartCheckout.services;

import com.jramanta.cartCheckout.model.Command;
import com.jramanta.cartCheckout.model.PricingRule;
import com.jramanta.cartCheckout.model.Product;
import com.jramanta.cartCheckout.model.WeeklyOffer;

import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PricingService {

    /**
     * Updates a products pricing according to the input rule
     *
     * @param pricingRuleInput the input as given by the application user
     * @param availableProducts map of all available products where key is the product code and value the product itself.
     * */
    public static void applyPricingRule(String pricingRuleInput, Map<String, Product> availableProducts)
            throws NoSuchMethodException {
        PricingRule pricingRule = parsePricingRule(pricingRuleInput)
                .orElseThrow(NoSuchMethodException::new);
        if (availableProducts.containsKey(pricingRule.getProductCode())) {
            setProductPricingFromRule(pricingRule, availableProducts);
        }

    }

    /**
     * Builds a PricingRule object based on the given rule input.
     *
     * @param pricingRule the input as given by the user
     * @return an optional {@code PricingRule}
     * */
    private static Optional<PricingRule> parsePricingRule(String pricingRule) {

        Optional<PricingRule> rule = Optional.empty();
        Pattern pattern = Pattern.compile(Command.UPDATE.getDescription());
        Matcher matcher = pattern.matcher(pricingRule);

        if (matcher.find()) {
            if (matcher.group(4) != null) {
                rule = Optional.of(new PricingRule(matcher.group(1),
                        Double.parseDouble(matcher.group(2)),
                        Integer.parseInt(matcher.group(5)),
                        Double.parseDouble(matcher.group(6))));
            } else {
                rule = Optional.of(new PricingRule(matcher.group(1),
                        Double.parseDouble(matcher.group(2))));
            }
        }

        return rule;
    }

    /**
     * If given rule's product code is equal to one of the existing products then the pricing and the offer.
     * */
    private static void setProductPricingFromRule(PricingRule rule, Map<String, Product> availableProducts) {
        if (availableProducts.containsKey(rule.getProductCode()) && rule.getProductPrice() != null
                && rule.getProductPrice() > 0) {
            Product aProduct = availableProducts.get(rule.getProductCode());
            aProduct.setUnitPrice(rule.getProductPrice());
            Optional<WeeklyOffer> productOffer = findProductOfferFromPricingRule(rule);
            if(productOffer.isPresent()) {
                aProduct.setWeeklyOffer(productOffer.get());
            } else {
                aProduct.setWeeklyOffer(null);
            }
        }
    }

    /**
     * Check whether the rule has a valid offer pricing. Price needs to be a positive number and the number of items in the offer
     * need to be more than one otherwise it would conflict with the unit price.
     *
     * @param rule a {@code PricingRule} object
     * @return an optional {@code WeeklyOffer}
     * */
    private static Optional<WeeklyOffer> findProductOfferFromPricingRule(PricingRule rule) {
        Optional<WeeklyOffer> weeklyOffer = Optional.empty();

        if (rule.getNumOfItems() != null && rule.getOfferPricing() != null &&
                rule.getNumOfItems() > 1 && rule.getOfferPricing() > 0) {
            weeklyOffer = Optional.of(new WeeklyOffer(rule.getNumOfItems(), rule.getOfferPricing()));
        }

        return weeklyOffer;
    }
}

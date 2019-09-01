package com.epam.factoryMethod;

import com.epam.factoryMethod.beverages.Beverage;
import com.epam.factoryMethod.beverages.Coffee;
import com.epam.factoryMethod.beverages.Tea;

import static java.util.Objects.requireNonNull;

public class BeverageFactory {

    private static final String DEFAULT_TEA_BREED = "plain tea";
    private static final String DEFAULT_COFFEE_BREED = "brazilian";

    private BeverageFactory() {
    }

    public static Beverage createBevarage(AllowedBeverages beverageName) {
        requireNonNull(beverageName);
        Beverage beverage;
        switch (beverageName) {
            case TEA:
                beverage = new Tea(1, DEFAULT_TEA_BREED);
                break;
            case COFFEE:
                beverage = new Coffee(1, DEFAULT_COFFEE_BREED, false);
                break;
            default:
                throw new IllegalArgumentException("We don't know this beverage.");
        }
        return beverage;
    }

    public enum AllowedBeverages {
        COFFEE,
        TEA
    }
}

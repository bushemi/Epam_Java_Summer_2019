package com.epam.factoryMethod.beverages;

public class Coffee implements Beverage {
    private int sugarCount;
    private String coffeeBreed;
    private boolean milk;


    public Coffee(int sugarCount, String coffeeBreed, boolean milk) {
        this.sugarCount = sugarCount;
        this.coffeeBreed = coffeeBreed;
        this.milk = milk;
    }

    @Override
    public String getName() {
        return "Coffee";
    }
}

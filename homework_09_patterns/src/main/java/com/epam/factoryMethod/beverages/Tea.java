package com.epam.factoryMethod.beverages;

public class Tea implements Beverage {

    private int sugarCount;
    private String teaBreed;

    public Tea(int sugarCount, String teaBreed) {
        this.sugarCount = sugarCount;
        this.teaBreed = teaBreed;
    }

    @Override
    public String getName() {
        return "Tea";
    }
}

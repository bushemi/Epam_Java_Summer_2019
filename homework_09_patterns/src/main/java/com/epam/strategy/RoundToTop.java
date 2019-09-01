package com.epam.strategy;

public class RoundToTop implements RoundStrategy {
    @Override
    public int round(double number) {
        return (int) Math.ceil(number);
    }
}

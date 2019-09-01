package com.epam.strategy;

public class RoundDown implements RoundStrategy {

    @Override
    public int round(double number) {
        return (int) Math.floor(number);
    }
}

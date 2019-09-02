package com.epam.strategy;

public class Cashier {
    public Cashier() {
    }

    public int getChange(int money, double price, RoundStrategy rounder) {
        double change = money - price;
        return rounder.round(change);
    }
}

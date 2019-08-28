package com.epam.decorator;

import static java.util.Objects.nonNull;

public abstract class AbstractPizza {
    private static final int BASE_COST = 10;
    private AbstractPizza pizza;
    private int cost = 0;

    public AbstractPizza(AbstractPizza pizza) {
        this.pizza = pizza;
    }

    public AbstractPizza() {
    }

    protected int getBaseCost() {
        return BASE_COST;
    }

    protected int cost() {
        int result = this.cost;
        if (nonNull(pizza)) {
            result += pizza.cost();
        } else {
            result += getBaseCost();
        }
        return result;
    }

}

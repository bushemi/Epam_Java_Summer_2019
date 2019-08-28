package com.epam.decorator.toppings;

import com.epam.decorator.AbstractPizza;

public class PineapplePizza extends AbstractPizza {
    private final int cost = 75;

    public PineapplePizza(AbstractPizza pizza) {
        super(pizza);
    }

    public PineapplePizza() {
    }

    @Override
    public int cost() {
        return super.cost() + cost;
    }
}

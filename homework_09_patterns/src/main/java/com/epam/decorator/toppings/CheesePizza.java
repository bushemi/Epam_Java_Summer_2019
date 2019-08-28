package com.epam.decorator.toppings;

import com.epam.decorator.AbstractPizza;

public class CheesePizza extends AbstractPizza {
    private final int cost = 5;

    public CheesePizza(AbstractPizza pizza) {
        super(pizza);
    }

    public CheesePizza() {
    }

    @Override
    public int cost() {
        return super.cost() + cost;
    }
}

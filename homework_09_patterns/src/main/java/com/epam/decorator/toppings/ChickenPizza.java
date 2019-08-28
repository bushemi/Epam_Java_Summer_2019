package com.epam.decorator.toppings;

import com.epam.decorator.AbstractPizza;

public class ChickenPizza extends AbstractPizza {
    private final int cost = 15;

    public ChickenPizza(AbstractPizza pizza) {
        super(pizza);
    }

    public ChickenPizza() {
    }

    @Override
    public int cost() {
        return super.cost() + cost;
    }
}

package com.epam.decorator.toppings;

import com.epam.decorator.AbstractPizza;

public class MushroomPizza extends AbstractPizza {
    private final int cost = 5;

    public MushroomPizza(AbstractPizza pizza) {
        super(pizza);
    }

    public MushroomPizza() {
    }

    @Override
    public int cost() {
        return super.cost() + cost;
    }
}

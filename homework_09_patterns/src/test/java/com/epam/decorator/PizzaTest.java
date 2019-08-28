package com.epam.decorator;

import com.epam.decorator.toppings.CheesePizza;
import com.epam.decorator.toppings.ChickenPizza;
import com.epam.decorator.toppings.MushroomPizza;
import com.epam.decorator.toppings.PineapplePizza;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class PizzaTest {

    private static final int PINEAPPLE_COST = 75;
    private static final int MUSHROOM_COST = 5;
    private static final int CHEESE_COST = 5;
    private static final int BASE_COST = 10;
    private static final int CHICKEN_COST = 15;

    @Test
    public void PizzaCostOnlyCheese() {
        //GIVEN
        AbstractPizza pizza = new CheesePizza();

        //WHEN
        int cost = pizza.cost();

        //THEN
        assertThat(cost, is(equalTo(BASE_COST + CHEESE_COST)));
    }

    @Test
    public void PizzaCostCheeseWithMushrooms() {
        //GIVEN
        AbstractPizza pizza = new CheesePizza();
        pizza = new MushroomPizza(pizza);

        //WHEN
        int cost = pizza.cost();

        //THEN
        assertThat(cost, is(equalTo(BASE_COST + CHEESE_COST + MUSHROOM_COST)));
    }

    @Test
    public void PizzaCostPineappleWithMushrooms() {
        //GIVEN
        AbstractPizza pizza = new PineapplePizza();
        pizza = new MushroomPizza(pizza);

        //WHEN
        int cost = pizza.cost();

        //THEN
        assertThat(cost, is(equalTo(BASE_COST + PINEAPPLE_COST + MUSHROOM_COST)));
    }

    @Test
    public void PizzaCostWithPineappleMushroomsCheeseAndChicken() {
        //GIVEN
        AbstractPizza pizza = new PineapplePizza();
        pizza = new MushroomPizza(pizza);
        pizza = new CheesePizza(pizza);
        pizza = new ChickenPizza(pizza);

        //WHEN
        int cost = pizza.cost();

        //THEN
        assertThat(cost, is(equalTo(BASE_COST + PINEAPPLE_COST + MUSHROOM_COST + CHEESE_COST + CHICKEN_COST)));
    }
}
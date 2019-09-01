package com.epam.factoryMethod;

import com.epam.factoryMethod.beverages.Beverage;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class BeverageFactoryTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void createCoffee() {
        //WHEN
        Beverage beverage = BeverageFactory.createBevarage(BeverageFactory.AllowedBeverages.COFFEE);

        //THEN
        assertThat(beverage.getName(), is(equalTo("Coffee")));
    }

    @Test
    public void createTea() {
        //WHEN
        Beverage beverage = BeverageFactory.createBevarage(BeverageFactory.AllowedBeverages.TEA);

        //THEN
        assertThat(beverage.getName(), is(equalTo("Tea")));
    }

    @Test
    public void createNullShouldReturnException() {
        //GIVEN
        expectedException.expect(NullPointerException.class);

        //WHEN
        BeverageFactory.createBevarage(null);

    }

}
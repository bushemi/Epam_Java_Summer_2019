package com.epam.strategy;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class RoundStrategyTest {
    @Test
    public void checkRoundToTop() {
        //GIVEN
        double number = 2.001;
        RoundStrategy round = new RoundToTop();

        //WHEN
        int roundedNumber = round.round(number);

        //THEN
        assertThat(roundedNumber, is(equalTo(3)));
    }

    @Test
    public void checkRoundDown() {
        //GIVEN
        double number = 2.99;
        RoundStrategy round = new RoundDown();

        //WHEN
        int roundedNumber = round.round(number);

        //THEN
        assertThat(roundedNumber, is(equalTo(2)));
    }

}
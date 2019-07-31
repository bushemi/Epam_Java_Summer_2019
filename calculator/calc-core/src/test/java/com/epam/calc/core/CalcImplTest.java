package com.epam.calc.core;


import com.epam.calc.interfaces.Calc;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

@RunWith(BlockJUnit4ClassRunner.class)
public class CalcImplTest {
    private Calc calculateService = new CalcImpl();

    @Test
    public void addition() {
        double result = calculateService.addition(4., 4.);
        Assert.assertEquals(8d, result, 0);
    }

    @Test
    public void additionMaxValue() {
        double result = calculateService.addition(Double.MAX_VALUE, Double.MAX_VALUE);
        Assert.assertTrue(Double.isInfinite(result));
    }

    @Test
    public void additionZero() {
        double result = calculateService.addition(0, 0);
        Assert.assertEquals(0, result, 0);
    }

    @Test
    public void subtraction() {
        double result = calculateService.subtraction(100, 10);
        Assert.assertEquals(90, result, 0);
    }

    @Test
    public void multiplication() {
        double result = calculateService.multiplication(100, 10);
        Assert.assertEquals(1000, result, 0);
    }

    @Test
    public void division() {
        double result = calculateService.division(100, 10);
        Assert.assertEquals(10, result, 0);
    }

    @Test
    public void divisionByZero() {
        double result = calculateService.division(100, 0);
        Assert.assertTrue(Double.isInfinite(result));
    }

    @Test
    public void divisionZeroByZero() {
        double result = calculateService.division(0, 0);
        Assert.assertTrue(Double.isInfinite(result));
    }

}
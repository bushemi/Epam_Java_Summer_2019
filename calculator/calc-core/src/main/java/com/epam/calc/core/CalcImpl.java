package com.epam.calc.core;

import com.epam.calc.interfaces.Calc;

public class CalcImpl implements Calc {

    public double addition(double a, double b) {
        return a + b;
    }

    public double subtraction(double a, double b) {
        return a - b;
    }

    public double multiplication(double a, double b) {
        return a * b;
    }

    public double division(double a, double b) {
        if (a == 0 && b == 0) return Double.NEGATIVE_INFINITY;
        return a / b;
    }
}

package pl.mo.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Polynomial {

    private List<Double> polynomialCoefficients;
    private long numberOfCalls;

    public Polynomial(Double... coefficients) {
        if (coefficients == null || coefficients.length == 0) {
            polynomialCoefficients = new ArrayList<>(Arrays.asList(1.0, -3.0, -15.0, 5.0));
            return;
        }

        polynomialCoefficients = new ArrayList<>(Arrays.asList(coefficients));
    }

    public double getValue(double argument) {
        double value = 0.0;
        int i;

        for (i = 0; i < polynomialCoefficients.size() - 1; i++) {
            value += polynomialCoefficients.get(i) * Math.pow(argument, (double) polynomialCoefficients.size() - (i + 1));
        }

        numberOfCalls++;
        return value + polynomialCoefficients.get(i);
    }

    public long getNumberOfCalls() {
        return numberOfCalls;
    }

}

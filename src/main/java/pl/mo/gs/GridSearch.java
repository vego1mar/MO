package pl.mo.gs;

import java.util.Arrays;
import java.util.List;

public class GridSearch {

    private List<Double> polynomialCoefficients;
    private long numberOfCalls;

    public GridSearch() {
        polynomialCoefficients = Arrays.asList(1.0, -3.0, -15.0, 5.0);
        numberOfCalls = 0;
    }

    public double getPolynomialValue(double argument) {
        double value = 0.0;
        int i;

        for (i = 0; i < polynomialCoefficients.size() - 1; i++) {
            value += polynomialCoefficients.get(i) * Math.pow(argument, Double.valueOf(polynomialCoefficients.size()) - (i + 1));
        }

        numberOfCalls++;
        return value + polynomialCoefficients.get(i);
    }

    public Double getLocalMinimumArgument(double left, double right, double epsilon) {
        final double ACCURACY = Math.abs(epsilon);

        for (double x = left + ACCURACY; x <= right; x += ACCURACY) {
            if (getPolynomialValue(x) > getPolynomialValue(x - ACCURACY)) {
                return x;
            }
        }

        return null;
    }

    public long getNumberOfCalls() {
        return numberOfCalls;
    }

}

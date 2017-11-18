package pl.mo.gs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
            value += polynomialCoefficients.get(i) * Math.pow(argument, (double) polynomialCoefficients.size() - (i + 1));
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

    /**
     * @throws java.util.NoSuchElementException when 'left' > 'right'
     * @throws IllegalArgumentException when 'delta' <= 0
     */
    public double getLocalMinimumArgument(double left, double right, double epsilon, int delta) {
        if (delta <= 0) {
            throw new IllegalArgumentException("Argument 'delta' must be greater than zero.");
        }

        if (Math.abs(right - left) <= Math.abs(epsilon)) {
            return left;
        }

        List<Double> arguments = new ArrayList<>();
        List<Double> values = new ArrayList<>();
        final Double step = (right - left) / delta;

        for (double i = left; i <= right; i += step) {
            double argument = getLocalMinimumArgument(i, i + step, Math.abs(epsilon), delta);
            arguments.add(argument);
            values.add(getPolynomialValue(argument));
        }

        return arguments.get(values.indexOf(Collections.min(values)));
    }

}

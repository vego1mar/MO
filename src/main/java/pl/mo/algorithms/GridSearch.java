package pl.mo.algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public strictfp class GridSearch extends LocalMinimumSearchAlgorithm {

    @Override
    public Double getLocalMinimumArgument(double left, double right, double epsilon) {
        final double ACCURACY = Math.abs(epsilon);

        for (double x = left + ACCURACY; x <= right; x += ACCURACY) {
            if (scoreFunction.getValue(x) > scoreFunction.getValue(x - ACCURACY)) {
                return x;
            }
        }

        return null;
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
            values.add(scoreFunction.getValue(argument));
        }

        return arguments.get(values.indexOf(Collections.min(values)));
    }

}

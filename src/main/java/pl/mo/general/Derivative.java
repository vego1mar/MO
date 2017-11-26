package pl.mo.general;

import org.jetbrains.annotations.Contract;
import pl.mo.algorithms.ScoreFunction;

public strictfp class Derivative {

    public static final double IBM_FLOAT_SURROUNDING = 5.96E-8;

    private Derivative() {
        // This should be a utility class.
    }

    /**
     * @throws IllegalArgumentException when 'function' is null.
     */
    @Contract("null, _, _ -> fail")
    public static double compute(ScoreFunction function, double argument, Double epsilon) {
        if (function == null) {
            throw new IllegalArgumentException("Argument 'function' is a null.");
        }

        if (epsilon == null) {
            epsilon = IBM_FLOAT_SURROUNDING;
        }

        double h = argument * Math.sqrt(Math.abs(epsilon));
        double value1 = function.getValue(argument + h);
        double value2 = function.getValue(argument - h);
        return (value1 - value2) / (2.0 * h);
    }

}

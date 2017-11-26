package pl.mo.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Polynomial extends ScoreFunction {

    private List<Double> coefficients;

    public Polynomial(Double... coefficients) {
        if (coefficients == null || coefficients.length == 0) {
            this.coefficients = new ArrayList<>(Arrays.asList(1.0, -3.0, -15.0, 5.0));
            return;
        }

        this.coefficients = new ArrayList<>(Arrays.asList(coefficients));
    }

    /**
     * @return f(x), where f(x) = ax<sup>n</sup> + bx<sup>n-1</sup> + ... + yx + z.
     */
    @Override
    public double getValue(double argument) {
        double value = 0.0;
        int i;

        for (i = 0; i < coefficients.size() - 1; i++) {
            value += coefficients.get(i) * Math.pow(argument, (double) coefficients.size() - (i + 1));
        }

        numberOfCalls++;
        return value + coefficients.get(i);
    }

    public List<Double> getGradientCoefficients() {
        List<Double> gradient = new ArrayList<>();

        for (int i = 0; i < coefficients.size() - 1; i++) {
            double currentExponent = coefficients.size() - (double) (i + 1);
            gradient.add(coefficients.get(i) * currentExponent);
        }

        return gradient;
    }

    /**
     * @return df(x), where f(x) = ax<sup>n</sup> + bx<sup>n-1</sup> + ... + yx + z.
     */
    public double getDifferential(double argument) {
        List<Double> gradient = getGradientCoefficients();
        double differential = 0.0;

        if (gradient.size() <= 1) {
            return differential;
        }

        for (int i = 0; i < gradient.size() - 1; i++) {
            differential += gradient.get(i) * Math.pow(argument, gradient.size() - (double) (i + 1));
        }

        differential += gradient.get(gradient.size() - 1);
        return differential;
    }

}

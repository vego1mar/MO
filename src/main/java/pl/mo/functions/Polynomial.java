package pl.mo.functions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import pl.mo.general.Power;

/**
 * Provides ways to define a real polynomial for the function optimization issues.<p></p>
 * In mathematics, a polynomial is an expression consisting of variables (indeterminates) and coefficients.
 * It can be written in the form:<tr></tr>
 * p<sub>n</sub> = q(x) = a<sub>n</sub>x<sup>n</sup> + a<sub>n-1</sub>x<sup>n-1</sup> + ... + a<sub>2</sub>x<sup>2</sup>
 * + a<sub>1</sub>x + a<sub>0</sub>,<tr></tr>
 * where:<tr></tr>
 * <b>a<sub>0</sub>, ... , a<sub>n</sub></b> are constants, and<tr></tr>
 * <b>x</b> is the indeterminate.<tr></tr>
 *
 * @version 1.3
 */
public strictfp class Polynomial extends ObjectiveFunction {

    public static final int MAXIMUM_ITERATIONS = 23_459;
    public static final double ROOT_FINDING_PRECISION = 10.0E-14;
    public static final double ROOT_FINDING_TOLERANCE = 10.0E-7;
    private List<Double> coefficients;

    /**
     * If no coefficient has been provided, then the default polynomial will be<tr></tr>
     * f(x) = x<sup>3</sup> - 3x<sup>2</sup> - 15x + 5.
     *
     * @param coefficients Variadic argument list of the polynomial coefficients. Provide them in the ascending order, that is: <p></p>
     *     <code>new Polynomial(2.0, 1.0, 3.0)</code><p></p>
     *     is an equivalent to f(x) = 2x<sup>2</sup> + x + 3.
     * @since 1.0
     */
    public Polynomial(Double... coefficients) {
        if (coefficients == null || coefficients.length == 0) {
            this.coefficients = new ArrayList<>(Arrays.asList(1.0, -3.0, -15.0, 5.0));
            return;
        }

        this.coefficients = new ArrayList<>(Arrays.asList(coefficients));
    }

    /**
     * @see #Polynomial(Double...)
     * @since 1.3
     */
    public Polynomial(List<Double> coefficients) {
        if (coefficients == null || coefficients.isEmpty()) {
            this.coefficients = new ArrayList<>(Arrays.asList(1.0, -3.0, -15.0, 5.0));
            return;
        }

        this.coefficients = coefficients;
    }

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

    /**
     * Obtain the coefficients of the first derivative of the given function.<tr></tr>
     * <pre>
     * Polynomial function = new Polynomial(2.0, 1.0, 0.0);
     * List&lt;Double&gt; gradient = function.getDerivativeCoefficients();
     * // f(x) = x<sup>2</sup> + x
     * // grad{f(x)} = 2x + 1
     * // gradient := [2, 1]
     * </pre>
     *
     * @return the first derivative coefficients in the ascending order.
     * @since 1.2
     */
    public List<Double> getDerivativeCoefficients() {
        List<Double> gradient = new ArrayList<>();

        for (int i = 0; i < coefficients.size() - 1; i++) {
            int currentExponent = coefficients.size() - (i + 1);
            gradient.add(coefficients.get(i) * currentExponent);
        }

        return gradient;
    }

    /**
     * @since 1.3
     */
    @Override
    public double getDifferential(double argument) {
        List<Double> gradient = getDerivativeCoefficients();
        double differential = 0.0;

        if (gradient.size() <= 1) {
            return differential;
        }

        for (int i = 0; i < gradient.size() - 1; i++) {
            differential += gradient.get(i) * Power.getFast(argument, gradient.size() - (i + 1));
        }

        differential += gradient.get(gradient.size() - 1);
        return differential;
    }

    /**
     * Newton's method (or the Newton-Raphson method) is a method for finding successively better
     * approximations to the roots of a real-valued function.<p></p>
     * Consider the polynomial of <b>f(x)</b> with derivative <b>f'(x)</b>, the initial guess
     * <b>x<sub>0</sub></b> and the number of iterations <b>k</b>. Then:<tr></tr>
     * <pre>
     * f(x) = x<sup>2</sup> - 612
     * f'(x) = 2x
     * x<sub>0</sub> = 10
     * x = ±6√17 ≈ +24.73863375370596329892
     * k = 5
     * </pre><tr></tr>
     * The Newton's method analysis is only guaranteed to converge if the theorem of quadratic convergence
     * conditions are satisfied. Failure means that the assumptions made were not met. The well-known
     * issues are:<p></p>
     * <ul>
     * <li>Bad starting points</li>
     * <ul>
     * <li>Stationary iteration point, i.e. f(x) = -x<sup>2</sup> + 1 and x<sub>0</sub> = 0</li>
     * <li>Entering a cycle, i.e. f(x) = x<sup>3</sup> - 2x + 2 and x<sub>0</sub> = 0</li>
     * </ul>
     * <li>Derivative issues (not for the standard polynomials)</li>
     * <ul>
     * <li>Not existing at root, i.e. f(x) = ∛x</li>
     * <li>Discontinuity</li>
     * </ul>
     * <li>Non-quadratic convergence (computational complexity problem)</li>
     * <ul>
     * <li>Zero derivative at the root, i.e. f(x) = x<sup>2</sup></li>
     * <li>Non-existing second derivative at the root, i.e. f(x) = x + x<sup>4/3</sup></li>
     * </ul>
     * </ul>
     * <p></p>
     *
     * @param initialGuess The starting point <b>x<sub>0</sub></b> that is likely to be a root value.
     * @return One root of the given polynomial, possibly nearest to the initial guess value.
     *     <b>null</b> occurs when the solution did not converge to the specified tolerance or the number
     *     of iterations exceeds the maximum specified value.
     * @since 1.3
     */
    public Double getRootByNewtonMethod(double initialGuess) {
        int k = 0;
        double x = initialGuess;
        double nextX;

        while (k < MAXIMUM_ITERATIONS) {
            double y = getValue(x);
            double df = getDifferential(x);

            if (Math.abs(df) < ROOT_FINDING_PRECISION) {
                return null;
            }

            nextX = x - (y / df);

            if (Math.abs(nextX - x) <= ROOT_FINDING_TOLERANCE * Math.abs(nextX)) {
                return nextX;
            }

            x = nextX;
            k++;
        }

        return null;
    }

    @Override
    public Number getValue(Number x, Number y) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Number> getDifferential(Number x, Number y) {
        throw new UnsupportedOperationException();
    }

}

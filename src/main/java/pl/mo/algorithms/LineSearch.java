package pl.mo.algorithms;

import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import pl.mo.general.Vectors;

/**
 * Gathers different methods of performing a line search to find the step size.
 *
 * @version 1.1
 */
public final strictfp class LineSearch {

    public static final int BACKTRACKING_MAXIMUM_ITERATIONS = 1_123;

    /**
     * The backtracking line search step ρ ∈ (0,1), that is a contraction factor
     * for the next iterate.
     *
     * @since 1.0
     */
    public static final double BACKTRACKING_STEP = 0.5;

    /**
     * The backtracking line search condition parameter c ∈ (0,1).
     *
     * @since 1.0
     */
    public static final double BACKTRACKING_PARAMETER = 1.0E-4;

    private LineSearch() {
        // This should be a utility class.
    }

    /**
     * Gets the value that minimizes (solves) function of <b>f(x<sub>k</sub> + tp<sub>k</sub>)</b>.
     * This method deals with one-dimensional functions.
     *
     * @param f A function definition of <b>f</b> that will be used
     * @param x A start point of <b>x<sub>k</sub> = x<sub>0</sub></b>
     * @param d A (descent) direction of search, namely <b>p<sub>k</sub> = p<sub>0</sub></b>
     * @return the function minimizer <b>t</b>, that is namely a step size
     * @since 1.1
     */
    public static double performBacktracking(ObjectiveFunction f, double x, double d) {
        int k = 0;
        double alfa = 1.0;
        double x0 = x;
        x = x0 + (alfa * d);

        while (k < BACKTRACKING_MAXIMUM_ITERATIONS) {
            if (isArmijoConditionSatisfied(f, x, alfa, d)) {
                break;
            }

            alfa = BACKTRACKING_STEP * alfa;
            x = x0 + (alfa * d);
            k++;
        }

        return alfa;
    }

    /**
     * Checks whether the line search step (contraction) gives a sufficient decrease in function
     * <b>f</b>. This condition is called Armijo or Wolfe condition and is expressed as follows:
     * <tr></tr><b>f(x<sub>k</sub> + ap<sub>k</sub>) ≤ f(x<sub>k</sub>) + c<sub>1</sub>a(∇f
     * <sub>k</sub>)<sup>T</sup>p<sub>k</sub></b><p></p>
     *
     * @param f is the function definition for calculating <b>f(x<sub>j</sub>)</b>
     * @param x is the start point of line search
     * @param t is the seeketh indeterminate, namely a step length
     * @param d is the line search descent direction
     * @return <b>true</b> when this condition has been fulfilled, <b>false</b> otherwise
     * @since 1.1
     */
    private static boolean isArmijoConditionSatisfied(@NotNull ObjectiveFunction f, double x, double t, double d) {
        double dg = f.getDifferential(x);
        double lhs = f.getValue(x + (t * d));
        double rhs = f.getValue(x) + (BACKTRACKING_PARAMETER * t * dg * d);
        return lhs <= rhs;
    }

    public static double performBacktracking(ObjectiveFunction f, List<Double> x, List<Double> d) {
        int k = 0;
        double alfa = 1.0;
        List<Double> x0 = new ArrayList<>(x);
        x = Vectors.add(x0, Vectors.multiplyByScalar(d, alfa));

        while (k < BACKTRACKING_MAXIMUM_ITERATIONS) {
            if (isArmijoConditionSatisfied(f, x, alfa, d)) {
                break;
            }

            alfa = BACKTRACKING_STEP * alfa;
            x = Vectors.add(x0, Vectors.multiplyByScalar(d, alfa));
            k++;
        }

        return alfa;
    }

    private static boolean isArmijoConditionSatisfied(@NotNull ObjectiveFunction f, List<Double> x, double t, List<Double> d) {
        List<Double> lhsVector = Vectors.add(x, Vectors.multiplyByScalar(d, t));
        Double lhs = Vectors.cast(f.getValue(lhsVector.get(0), lhsVector.get(1)), Double.class);
        Double leftRhs = Vectors.cast(f.getValue(x.get(0), x.get(1)), Double.class);
        Double rightRhs = BACKTRACKING_PARAMETER * t * Vectors.multiplyAsMatrix(x, d);
        Double rhs = leftRhs + rightRhs;
        return lhs <= rhs;
    }

}

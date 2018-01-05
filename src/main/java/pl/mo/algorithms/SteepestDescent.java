package pl.mo.algorithms;

import java.util.ArrayList;
import java.util.List;
import pl.mo.general.Vectors;

/**
 * The steepest descent method (also called the stationary-phase method or saddle-point method), which can be traced back to
 * Cauchy (1847), is the simplest gradient method for unconstrained optimization.
 *
 * @version 1.1
 */
public strictfp class SteepestDescent extends LocalMinimumSearchAlgorithm {

    public static final int MAXIMUM_ITERATIONS = 104_729;
    public static final double CONSTANT_GRADIENT_CONVERGENCE = 1.0E-13;
    public static final double MUTABLE_GRADIENT_CONVERGENCE = 1.0E-8;
    public static final double MINIMIZER_STEP = 0.01;
    private int iterationsNo;

    /**
     * Steepest descent algorithm specialization for real one-dimensional functions.
     * The method performs the default specified number of iterations until the gradient convergence condition
     * will be satisfied.
     *
     * @param startPoint The initial guess argument <b>x<sub>0</sub></b>.
     * @param isConstantMinimizerAllowed If <b>false</b> then the backtracking line search will be performed, otherwise
     *     the default constant value will be used.
     * @return the function argument where the local minimum occurs
     * @since 1.1
     */
    public double getLocalMinimumArgument(double startPoint, boolean isConstantMinimizerAllowed) {
        int k = 0;
        double x = startPoint;
        double minimizer = MINIMIZER_STEP;
        double nextX = x;
        iterationsNo = 0;
        final double GRADIENT_CONVERGENCE = isConstantMinimizerAllowed ? CONSTANT_GRADIENT_CONVERGENCE : MUTABLE_GRADIENT_CONVERGENCE;

        while (k < MAXIMUM_ITERATIONS) {
            x = nextX;
            double dg = objectiveFunction.getDifferential(x);
            double normL2 = Math.abs(dg);
            double direction = -dg;

            if (normL2 <= GRADIENT_CONVERGENCE) {
                iterationsNo = k;
                return nextX;
            }

            if (!isConstantMinimizerAllowed) {
                minimizer = LineSearch.performBacktracking(objectiveFunction, x, direction);
            }

            nextX = x + (minimizer * direction);
            k++;
        }

        iterationsNo = k;
        return nextX;
    }

    public int getIterationsNo() {
        return iterationsNo;
    }

    public List<Double> getLocalMinimumArgument(List<Double> startPoint, boolean isConstantMinimizerAllowed) {
        int k = 0;
        List<Double> x = new ArrayList<>(startPoint);
        double minimizer = MINIMIZER_STEP;
        List<Double> nextX = new ArrayList<>(x);
        iterationsNo = 0;
        final double GRADIENT_CONVERGENCE = isConstantMinimizerAllowed ? CONSTANT_GRADIENT_CONVERGENCE : MUTABLE_GRADIENT_CONVERGENCE;

        while (k < MAXIMUM_ITERATIONS) {
            x = nextX;
            List<Number> dg = objectiveFunction.getDifferential(x.get(0), x.get(1));
            Double normL2 = Vectors.getNorm(dg, 2);
            List<Number> direction = Vectors.negate(dg);

            if (normL2 <= GRADIENT_CONVERGENCE) {
                iterationsNo = k;
                return nextX;
            }

            if (!isConstantMinimizerAllowed) {
                minimizer = LineSearch.performBacktracking(objectiveFunction, x, Vectors.cast(direction, Double.class));
            }

            nextX.set(0, x.get(0) + (minimizer * direction.get(0).doubleValue()));
            nextX.set(1, x.get(1) + (minimizer * direction.get(1).doubleValue()));
            k++;
        }

        iterationsNo = k;
        return nextX;
    }

}

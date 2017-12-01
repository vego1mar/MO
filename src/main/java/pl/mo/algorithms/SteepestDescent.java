package pl.mo.algorithms;

import org.apache.log4j.Logger;

/**
 * The steepest descent method (also called the stationary-phase method or saddle-point method), which can be traced back to
 * Cauchy (1847), is the simplest gradient method for unconstrained optimization.
 *
 * @version 1.1
 */
public strictfp class SteepestDescent extends LocalMinimumSearchAlgorithm {

    public static final int MAXIMUM_ITERATIONS = 104_729;
    public static final double GRADIENT_CONVERGENCE = 1.0E-13;
    public static final double MINIMIZER_STEP = 0.01;
    private static final Logger log = Logger.getLogger(SteepestDescent.class);
    private int iterationsNo;

    /**
     * @throws UnsupportedOperationException always
     * @since 1.0
     */
    @Override
    public Double getLocalMinimumArgument(double left, double right, double epsilon) {
        throw new UnsupportedOperationException("Method overwritten, yet has no use.");
    }

    /**
     * Steepest descent algorithm specialization for real polynomial functions (one-dimensional).
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
        log.debug("startPoint=" + startPoint + "; minimizer=" + minimizer + "; MAXIMUM_ITERATIONS=" + MAXIMUM_ITERATIONS);
        log.debug("scoreFunction.getDerivativeCoefficients()=" + scoreFunction.getDerivativeCoefficients());

        while (k < MAXIMUM_ITERATIONS) {
            x = nextX;
            double dg = scoreFunction.getDifferential(x);
            double normL2 = Math.abs(dg);
            double direction = -dg;

            if (normL2 <= GRADIENT_CONVERGENCE) {
                log.debug("normL2=" + normL2 + " <= GRADIENT_CONVERGENCE=" + GRADIENT_CONVERGENCE);
                return nextX;
            }

            if (!isConstantMinimizerAllowed) {
                // TODO: backtracking line search
                log.debug("TODO block");
            }

            nextX = x + (minimizer * direction);
            log.debug("k=" + k + "; dg=" + dg + "; x(" + k + ")=" + nextX);
            k++;
        }

        iterationsNo = k;
        log.debug("return exceeded; iterationsNo=" + getIterationsNo());
        return nextX;
    }

    /**
     * Gets the number of iterations for the most recent function call.
     *
     * @return the number of iterations performed
     * @see #getLocalMinimumArgument(double, boolean)
     * @since 1.1
     */
    public int getIterationsNo() {
        return iterationsNo;
    }

}

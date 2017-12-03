package pl.mo.algorithms;

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
     * @throws UnsupportedOperationException always
     * @since 1.0
     */
    @Override
    public Double getLocalMinimumArgument(double left, double right, double epsilon) {
        throw new UnsupportedOperationException("Method overwritten, yet has no use.");
    }

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
            double dg = scoreFunction.getDifferential(x);
            double normL2 = Math.abs(dg);
            double direction = -dg;

            if (normL2 <= GRADIENT_CONVERGENCE) {
                iterationsNo = k;
                return nextX;
            }

            if (!isConstantMinimizerAllowed) {
                minimizer = LineSearch.performBacktracking(scoreFunction, x, direction);
            }

            nextX = x + (minimizer * direction);
            k++;
        }

        iterationsNo = k;
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

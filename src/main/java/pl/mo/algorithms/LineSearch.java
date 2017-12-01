package pl.mo.algorithms;

import org.jetbrains.annotations.Contract;

public final strictfp class LineSearch {

    public static final int BACKTRACKING_MAXIMUM_ITERATIONS = 1_123;

    /* ρ ∈ (0,1) */
    public static final double BACKTRACKING_MINIMIZER_MULTIPLIER = 0.5;

    /* c ∈ (0,1) */
    public static final double BACKTRACKING_STEP = 10.0E-4;

    private LineSearch() {
        // This should be a utility class.
    }

    /**
     * Gets the value that minimizes (solves) function of <b>f(x<sub>k</sub> + αp<sub>k</sub>)</b>.
     * This method deals with one-dimensional functions.
     * The direction chosen is negative descent direction (negative gradient).
     *
     * @param function definition of <b>f</b> that will be used
     * @param startPoint of <b>x<sub>0</sub></b>
     * @param startMinimizer that should be <b>α<sub>0</sub> > 0</b>
     * @return the specified function minimizer, that is namely a step size
     */
    @Contract(pure = true)
    public static double performBacktracking(ScoreFunction function, double startPoint, double startMinimizer) {
        double alfa = Math.abs(startMinimizer);
        int k = 0;

        while (k < BACKTRACKING_MAXIMUM_ITERATIONS) {
            if (isBacktrackingConditionSatisfied(function, startPoint, alfa)) {
                break;
            }

            alfa = BACKTRACKING_MINIMIZER_MULTIPLIER * alfa;
            k++;

        }

        return alfa;
    }

    private static boolean isBacktrackingConditionSatisfied(ScoreFunction f, double x, double alfa) {
        double dg = f.getDifferential(x);
        double direction = -dg;
        double left = f.getValue(x + (alfa * direction));
        double right = f.getValue(x) + (BACKTRACKING_STEP * alfa * dg * direction);
        return left <= right;
    }

}

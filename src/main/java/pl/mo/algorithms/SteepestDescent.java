package pl.mo.algorithms;

public strictfp class SteepestDescent extends LocalMinimumSearchAlgorithm {

    public static final int MAXIMUM_ITERATIONS = 32_141;
    public static final double GRADIENT_CONVERGENCE = 1E-5;
    public static final double ABSOLUTE_TOLERANCE = 1E-6;
    public static final double RELATIVE_TOLERANCE = 0.01;

    /**
     * @deprecated due to insufficient method parameters.
     */
    @Deprecated(since = "1.0", forRemoval = false)
    @Override
    public Double getLocalMinimumArgument(double left, double right, double epsilon) {
        return null;
    }

    /**
     * Steepest descent algorithm specialization for polynomial function (one-dimensional).
     *
     * @deprecated due to the outputting improper results
     */
    @Deprecated(since = "1.0", forRemoval = true)
    public Double getLocalMinimumArgument(double startPoint) {
        int k = 0;
        double functionGradient;
        double currentArgument = startPoint;
        double previousArgument;
        double gradientNormL2;
        double normalizedSearchDirection;
        double stepSize;

        while (k < MAXIMUM_ITERATIONS) {
            functionGradient = scoreFunction.getDifferential(currentArgument);
            gradientNormL2 = functionGradient * functionGradient;

            if (gradientNormL2 < GRADIENT_CONVERGENCE) {
                break;
            }

            normalizedSearchDirection = (-functionGradient) / gradientNormL2;
            stepSize = performLineSearch(currentArgument, normalizedSearchDirection);
            previousArgument = currentArgument;
            currentArgument = currentArgument + (stepSize * normalizedSearchDirection);

            if (isFunctionSuccessivelyReduced(previousArgument, currentArgument) && k >= 2) {
                break;
            }

            k++;
            currentArgument = previousArgument + 1;
        }

        return currentArgument;
    }

    private double performLineSearch(double argument, double direction) {
        for (double step = 0.0; step < Double.MAX_VALUE; step += GRADIENT_CONVERGENCE) {
            if (scoreFunction.getValue(argument + (step * direction)) < scoreFunction.getValue(argument)) {
                return step;
            }
        }

        return -Double.MIN_VALUE;
    }

    private boolean isFunctionSuccessivelyReduced(double previousArgument, double currentArgument) {
        double leftOperand = Math.abs(scoreFunction.getValue(currentArgument) - scoreFunction.getValue(previousArgument));
        double rightOperand = ABSOLUTE_TOLERANCE + (RELATIVE_TOLERANCE * scoreFunction.getValue(previousArgument));
        return leftOperand < rightOperand;
    }

}

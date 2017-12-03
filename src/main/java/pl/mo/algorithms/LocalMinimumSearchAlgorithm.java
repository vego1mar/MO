package pl.mo.algorithms;

public abstract class LocalMinimumSearchAlgorithm {

    protected Polynomial scoreFunction;

    protected LocalMinimumSearchAlgorithm() {
        scoreFunction = new Polynomial();
    }

    public Polynomial getScoreFunction() {
        return scoreFunction;
    }

    /**
     * Gets the argument of the local minimum for the given score function.
     * @param left Lower limit, left <b>x</b> of the search interval.
     * @param right Upper limit, right <b>x</b> of the search interval.
     * @param epsilon The argument searching floating point precision.
     * @return function's local minimum argument.
     * @since 1.0
     */
    public abstract Double getLocalMinimumArgument(double left, double right, double epsilon);

}

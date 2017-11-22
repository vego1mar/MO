package pl.mo.algorithms;

public abstract class LocalMinimumSearchAlgorithm {

    protected Polynomial scoreFunction;

    protected LocalMinimumSearchAlgorithm() {
        scoreFunction = new Polynomial();
    }

    public Polynomial getScoreFunction() {
        return scoreFunction;
    }

    public long getScoreFunctionInvocations() {
        return scoreFunction.getNumberOfCalls();
    }

    public abstract Double getLocalMinimumArgument(double left, double right, double epsilon);

}

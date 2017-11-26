package pl.mo.algorithms;

public abstract class ScoreFunction {

    protected long numberOfCalls;

    public long getNumberOfCalls() {
        return numberOfCalls;
    }

    public abstract double getValue(double argument);

}

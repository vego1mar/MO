package pl.mo.functions;

import java.util.List;

public abstract class ObjectiveFunction {

    protected long numberOfCalls;

    public long getNumberOfCalls() {
        return numberOfCalls;
    }

    public abstract double getValue(double argument);

    public abstract Number getValue(Number x, Number y);

    public abstract double getDifferential(double argument);

    public abstract List<Number> getDifferential(Number x, Number y);

}

package pl.mo.algorithms;

/**
 * An abstract for enforcing requirements for the score function (or target function).
 *
 * @version 1.1
 */
public abstract class ScoreFunction {

    protected long numberOfCalls;

    public long getNumberOfCalls() {
        return numberOfCalls;
    }

    /**
     * Obtain the function value at the given argument.
     *
     * @param argument The argument <b>x</b> of the abscissa (the X-axis).
     * @return <b>f(x)</b>, where <b>f</b> is the given function definition
     * @since 1.0
     */
    public abstract double getValue(double argument);

    /**
     * Obtain the function value of its differential at the given argument.
     *
     * @param argument The argument <b>x</b> of the abscissa (the X-axis).
     * @return <b>df(x)</b>, where <b>f(x)</b> is the given function definition.
     * @since 1.1
     */
    public abstract double getDifferential(double argument);

}

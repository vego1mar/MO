package pl.mo.algorithms;

import java.util.List;

/**
 * An abstract for enforcing requirements for the score function (or target function).
 *
 * @version 1.2
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
     * Obtain the function value at the given argument.<p></p>
     *
     * @param x is the first argument dimension
     * @param y is the second argument dimension
     * @return <b>f(x,y)</b>, where <b>f</b> is the given function definition
     * @since 1.2
     */
    public abstract Number getValue(Number x, Number y);

    /**
     * Obtain the function value of its differential at the given argument.
     *
     * @param argument The argument <b>x</b> of the abscissa (the X-axis).
     * @return <b>df(x)</b>, where <b>f(x)</b> is the given function definition.
     * @since 1.1
     */
    public abstract double getDifferential(double argument);


    /**
     * Obtain the function value of its differential at the given argument.<p></p>
     *
     * @param x is the first dimension argument
     * @param y is the second dimension argument
     * @return <b>df(x,y)</b> = [df/dx, df/dy], where <b>f</b> is the given
     *     function definition
     */
    public abstract List<Number> getDifferential(Number x, Number y);

}

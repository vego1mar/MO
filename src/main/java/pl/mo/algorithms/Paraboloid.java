package pl.mo.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import pl.mo.strings.ParaboloidBundle;

/**
 * Provides ways to define a real-based or integer-based elliptic paraboloid.<p></p>
 * In geometry, a paraboloid is a quadric surface that has exactly one axis of symmetry and
 * no center of symmetry. There are two kinds of paraboloids, elliptic and hyperbolic, depending
 * on the nature of the planar cross sections: a paraboloid is elliptic if almost all cross
 * sections are ellipses; it is hyperbolic if almost all cross sections are hyperbolas.<p></p>
 * An <b>elliptic paraboloid</b> is shaped like an oval cup and has a maximum or minimum point
 * when its axis is vertical. A <b>hyperbolic paraboloid</b> is a doubly ruled surface shaped
 * like a saddle. Both paraboloids can be represented by the equations:<p></p>
 * z = x<sup>2</sup>/a<sup>2</sup> + y<sup>2</sup>/b<sup>2</sup> <b>or</b> f(x, y) = c
 * <sub>1</sub>(x - c<sub>3</sub>)<sup>2</sup> + c<sub>4</sub>(y - c<sub>5</sub>)<sup>2</sup>
 * + c<sub>6</sub><p></p>
 * z = x<sup>2</sup>/a<sup>2</sup> - y<sup>2</sup>/b<sup>2</sup> <b>or</b> f(x,y) = c
 * <sub>1</sub>(x - c<sub>3</sub>)<sup>2</sup> - c<sub>4</sub>(y - c<sub>5</sub>)<sup>2</sup>
 * + c<sub>6</sub><p></p>
 *
 * @version 1.0
 */
public strictfp class Paraboloid extends ObjectiveFunction {

    private ParaboloidBundle bundle = new ParaboloidBundle();
    private Double[] parameters;

    /**
     * Provide a way to define the paraboloid function expressed as follows:<tr></tr>
     * f(x,y) = a(dx + f)<sup>2</sup> + b(ey + g)<sup>2</sup> + c
     * <p></p>
     *
     * @param a is the first subtraction square factor
     * @param d is the first subtraction square dimension <b>x</b> subtrahend
     * @param f is the first dimension <b>x</b> multiplier
     * @param b is the second subtraction square factor
     * @param e is the second subtraction square dimension <b>y</b> subtrahend
     * @param g is the second dimension <b>y</b> multiplier
     * @param c is the free expression
     * @since 1.0
     */
    public Paraboloid(double a, double d, double f, double b, double e, double g, double c) {
        parameters = new Double[]{a, d, f, b, e, g, c};
    }

    /**
     * Constructs the default elliptic paraboloid with a following function definition:<p></p>
     * f(x,y) = 2(x - 2)<sup>2</sup> + (y - 1)<sup>2</sup> + 3<p></p>
     *
     * @since 1.0
     */
    public Paraboloid() {
        parameters = new Double[]{2.0, 1.0, -2.0, 1.0, 1.0, -1.0, 3.0};
    }

    /**
     * @throws UnsupportedOperationException always
     * @since 1.0
     */
    @Override
    public double getValue(double argument) {
        throw new UnsupportedOperationException(bundle.getTextOverwrittenMethods());
    }

    /**
     * Note, that only <b>Double</b> type is allowed.<p></p>
     *
     * @throws NullPointerException if any of the arguments are <b>null</b>
     * @since 1.0
     */
    @Override
    public Number getValue(Number x, Number y) {
        if (x instanceof Double && y instanceof Double) {
            numberOfCalls++;
            x = (getParameterD() * x.doubleValue()) + getParameterF();
            x = getParameterA() * x.doubleValue() * x.doubleValue();
            y = (getParameterE() * y.doubleValue()) + getParameterG();
            y = getParameterB() * y.doubleValue() * y.doubleValue();
            return x.doubleValue() + y.doubleValue() + getParameterC();
        }

        return null;
    }

    public Double getParameterA() {
        return parameters[0];
    }

    public Double getParameterD() {
        return parameters[1];
    }

    public Double getParameterF() {
        return parameters[2];
    }

    public Double getParameterB() {
        return parameters[3];
    }

    public Double getParameterE() {
        return parameters[4];
    }

    public Double getParameterG() {
        return parameters[5];
    }

    public Double getParameterC() {
        return parameters[6];
    }

    /**
     * @throws UnsupportedOperationException always
     * @since 1.0
     */
    @Override
    public double getDifferential(double argument) {
        throw new UnsupportedOperationException(bundle.getTextOverwrittenMethods());
    }

    /**
     * Note, that only <b>Double</b> type is allowed.<p></p>
     *
     * @throws NullPointerException if any of the arguments are <b>null</b>
     * @since 1.0
     */
    @Override
    public List<Number> getDifferential(Number x, Number y) {
        if (x instanceof Double && y instanceof Double) {
            x = (getParameterD() * x.doubleValue()) + getParameterF();
            x = (2.0 * getParameterA()) * x.doubleValue();
            y = (getParameterE() * y.doubleValue()) + getParameterG();
            y = (2.0 * getParameterB()) * y.doubleValue();
            return new ArrayList<>(Arrays.asList(x, y));
        }

        return Collections.emptyList();
    }

}

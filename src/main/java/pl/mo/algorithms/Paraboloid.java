package pl.mo.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import pl.mo.window.AppTextsStockroom;
import pl.mo.general.PrimitivesHelper;

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
public strictfp class Paraboloid extends ScoreFunction {

    private Object[] parameters;

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
    public Paraboloid(int a, int d, int f, int b, int e, int g, int c) {
        parameters = new Integer[]{a, d, f, b, e, g, c};
    }

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
        parameters = new Integer[]{2, 1, -2, 1, 1, -1, 3};
    }

    /**
     * @throws UnsupportedOperationException always
     * @since 1.0
     */
    @Override
    public double getValue(double argument) {
        throw new UnsupportedOperationException(AppTextsStockroom.getInstance().getParaboloid().getMsgOverwrittenMethod());
    }

    /**
     * Note, that only <b>Integer</b> and <b>Double</b> types are allowed.<p></p>
     *
     * @since 1.0
     */
    @Override
    public Number getValue(Number x, Number y) {
        if (x instanceof Integer && y instanceof Integer) {
            x = (getParameterD(Integer.class) * x.intValue()) + getParameterF(Integer.class);
            x = getParameterA(Integer.class) * x.intValue() * x.intValue();
            y = (getParameterE(Integer.class) * y.intValue()) + getParameterG(Integer.class);
            y = getParameterB(Integer.class) * y.intValue() * y.intValue();
            return x.intValue() + y.intValue() + getParameterC(Integer.class);
        }

        if (x instanceof Double && y instanceof Double) {
            x = (getParameterD(Double.class) * x.doubleValue()) + getParameterF(Double.class);
            x = getParameterA(Double.class) * x.doubleValue() * x.doubleValue();
            y = (getParameterE(Double.class) * y.doubleValue()) + getParameterG(Double.class);
            y = getParameterB(Double.class) * y.doubleValue() * y.doubleValue();
            return x.doubleValue() + y.doubleValue() + getParameterC(Double.class);
        }

        return null;
    }

    public <T extends Number> T getParameterA(Class<T> tClass) {
        return PrimitivesHelper.castInto(parameters[0], tClass);
    }

    public <T extends Number> T getParameterD(Class<T> tClass) {
        return PrimitivesHelper.castInto(parameters[1], tClass);
    }

    public <T extends Number> T getParameterF(Class<T> tClass) {
        return PrimitivesHelper.castInto(parameters[2], tClass);
    }

    public <T extends Number> T getParameterB(Class<T> tClass) {
        return PrimitivesHelper.castInto(parameters[3], tClass);
    }

    public <T extends Number> T getParameterE(Class<T> tClass) {
        return PrimitivesHelper.castInto(parameters[4], tClass);
    }

    public <T extends Number> T getParameterG(Class<T> tClass) {
        return PrimitivesHelper.castInto(parameters[5], tClass);
    }

    public <T extends Number> T getParameterC(Class<T> tClass) {
        return PrimitivesHelper.castInto(parameters[6], tClass);
    }

    /**
     * @throws UnsupportedOperationException always
     * @since 1.0
     */
    @Override
    public double getDifferential(double argument) {
        throw new UnsupportedOperationException(AppTextsStockroom.getInstance().getParaboloid().getMsgOverwrittenMethod());
    }

    /**
     * Note, that only <b>Integer</b> and <b>Double</b> types are allowed.<p></p>
     *
     * @since 1.0
     */
    @Override
    public List<Number> getDifferential(Number x, Number y) {
        if (x instanceof Integer && y instanceof Integer) {
            x = (getParameterD(Integer.class) * x.intValue()) + getParameterF(Integer.class);
            x = (2 * getParameterA(Integer.class)) * x.intValue();
            y = (getParameterE(Integer.class) * y.intValue()) + getParameterG(Integer.class);
            y = (2 * getParameterB(Integer.class)) * y.intValue();
            return new ArrayList<>(Arrays.asList(x, y));
        }

        if (x instanceof Double && y instanceof Double) {
            x = (getParameterD(Double.class) * x.doubleValue()) + getParameterF(Double.class);
            x = (2.0 * getParameterA(Double.class)) * x.doubleValue();
            y = (getParameterE(Double.class) * y.doubleValue()) + getParameterG(Double.class);
            y = (2.0 * getParameterB(Double.class)) * y.doubleValue();
            return new ArrayList<>(Arrays.asList(x, y));
        }

        return Collections.emptyList();
    }

}

package pl.mo.functions;

import java.util.List;

/**
 * f(x,y) = (x<sup>2</sup> + y - 11)<sup>2</sup> + (x + y<sup>2</sup> - 7)<sup>2</sup>
 * <p></p>
 * Local maximum:<br></br>
 * f(-0.270845, -0.923039) = 181.617<br></br>
 * <p></p>
 * Local minimum:<br></br>
 * f(3, 2) = 0.0<br></br>
 * f(-2.805118, 3.131312) = 0.0<br></br>
 * f(-3.779310, -3.283186) = 0.0<br></br>
 * f(3.584428, -1.848126) = 0.0<br></br>
 * <p></p>
 */
public strictfp class Himmelblau extends ObjectiveFunction {

    @Override
    public double getValue(double argument) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Number getValue(Number x, Number y) {
        if (x == null || y == null) {
            return null;
        }

        Number operand1 = x.doubleValue() * x.doubleValue();
        operand1 = operand1.doubleValue() + y.doubleValue() - 11.0;
        operand1 = operand1.doubleValue() * operand1.doubleValue();
        Number operand2 = y.doubleValue() * y.doubleValue();
        operand2 = x.doubleValue() + operand2.doubleValue() - 7.0;
        operand2 = operand2.doubleValue() * operand2.doubleValue();
        return operand1.doubleValue() + operand2.doubleValue();
    }

    @Override
    public double getDifferential(double argument) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Number> getDifferential(Number x, Number y) {
        throw new UnsupportedOperationException();
    }

}

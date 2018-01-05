package pl.mo.general;

import static org.hamcrest.Matchers.closeTo;

import org.junit.Assert;
import org.junit.Test;
import pl.mo.functions.Polynomial;

public class DerivativeTest {

    @Test
    public void compute1() {
        // given f(x) = x^2
        final double EPSILON = 1E-11;
        Polynomial function = new Polynomial(1.0, 0.0, 0.0);

        // when
        double value1 = Derivative.compute(function, Math.sqrt(3.0), null);
        double value2 = Derivative.compute(function, Math.sqrt(4.0), EPSILON);

        // then
        Assert.assertThat(value1, closeTo(2.0 * Math.sqrt(3.0), Derivative.IBM_FLOAT_SURROUNDING));
        Assert.assertThat(value2, closeTo(4.0, EPSILON));
    }

    @Test(expected = IllegalArgumentException.class)
    public void compute2() {
        // when
        Derivative.compute(null, 0.0, 0.0);
    }

}

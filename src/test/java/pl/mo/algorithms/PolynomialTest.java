package pl.mo.algorithms;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.closeTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.spy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

public class PolynomialTest {

    private static final double IBM_FLOAT_SURROUNDING = 5.96E-8;

    @Test
    public void ctor1() {
        // when
        Polynomial object1 = new Polynomial();
        Polynomial object3 = new Polynomial(-0.0);
        Polynomial object4 = new Polynomial(1.0, 3.0);
        Polynomial object5 = new Polynomial(-11.1, 22.2, 123.45);

        // then
        Assert.assertNotNull(object1);
        Assert.assertNotNull(object3);
        Assert.assertNotNull(object4);
        Assert.assertNotNull(object5);
    }

    @Test
    public void ctor2() {
        // when
        Polynomial object1 = new Polynomial();
        Polynomial object3 = new Polynomial(Collections.singletonList(-0.0));
        Polynomial object4 = new Polynomial(Arrays.asList(1.0, 3.0));
        Polynomial object5 = new Polynomial(Arrays.asList(-11.1, 22.2, 123.45));

        // then
        Assert.assertNotNull(object1);
        Assert.assertNotNull(object3);
        Assert.assertNotNull(object4);
        Assert.assertNotNull(object5);
    }

    @Test
    public void getValue() {
        // given
        Polynomial object1 = spy(new Polynomial());
        Polynomial object3 = spy(new Polynomial(0.0));
        Polynomial object4 = spy(new Polynomial(0.0, 1.0));
        Polynomial object5 = spy(new Polynomial(0.0, 1.0, 2.0));
        Polynomial object6 = spy(new Polynomial(Arrays.asList(1.0, -30.0, 167.0, -138.0)));

        // when
        Double value1 = object1.getValue(10.0);
        Double value3 = object3.getValue(Double.MAX_VALUE);
        Double value4 = object4.getValue(Double.MIN_VALUE);
        Double value5 = object5.getValue(0.0);
        Double value6 = object6.getValue(1.1);

        // then
        assertThat(value1, closeTo(555.0, IBM_FLOAT_SURROUNDING));
        assertThat(value3, closeTo(0.0, IBM_FLOAT_SURROUNDING));
        assertThat(value4, closeTo(1.0, IBM_FLOAT_SURROUNDING));
        assertThat(value5, closeTo(2.0, IBM_FLOAT_SURROUNDING));
        assertThat(value6, closeTo(10.731, IBM_FLOAT_SURROUNDING));
    }

    @Test
    public void getNumberOfCalls() {
        // given
        Polynomial object1 = spy(new Polynomial());
        Polynomial object3 = spy(new Polynomial(0.0));
        Polynomial object4 = spy(new Polynomial(0.0, 1.0));
        Polynomial object5 = spy(new Polynomial(0.0, 1.0, 2.0));

        // when
        object1.getValue(anyDouble());
        object3.getValue(anyDouble());
        object4.getValue(anyDouble());
        object5.getValue(anyDouble());
        object5.getValue(anyDouble());

        // then
        assertEquals(1L, object1.getNumberOfCalls());
        assertEquals(1L, object3.getNumberOfCalls());
        assertEquals(1L, object4.getNumberOfCalls());
        assertEquals(2L, object5.getNumberOfCalls());
    }

    @Test
    public void getDerivativeCoefficients() {
        // given
        Polynomial polynomial1 = new Polynomial(1.0, 5.0, 1.0, 1.0); // f(x) = x^3 + 5x^2 + x + 1
        Polynomial polynomial2 = new Polynomial(2.0, 0.0, 0.0); // f(x) = 2x^2
        Polynomial polynomial3 = new Polynomial(7.0, 1.0); // f(x) = 7x + 1
        Polynomial polynomial4 = new Polynomial(1.0); // f(x) = 1
        Polynomial polynomial5 = new Polynomial(2.0, 0.0, -3.0, 0.0, 0.0, 4.0); // f(x) = 2x^5 + 3x^3 + 4

        // when
        List<Double> gradient1 = polynomial1.getDerivativeCoefficients();
        List<Double> gradient2 = polynomial2.getDerivativeCoefficients();
        List<Double> gradient3 = polynomial3.getDerivativeCoefficients();
        List<Double> gradient4 = polynomial4.getDerivativeCoefficients();
        List<Double> gradient5 = polynomial5.getDerivativeCoefficients();

        // then
        assertThat(gradient1, is(Arrays.asList(3.0, 10.0, 1.0))); // f'(x) = 3x^2 + 10x + 1
        assertThat(gradient2, is(Arrays.asList(4.0, 0.0))); // f'(x) = 4x
        assertThat(gradient3, is(Collections.singletonList(7.0))); // f'(x) = 7
        assertThat(gradient4, is(Collections.emptyList())); // f'(x) = 0
        assertThat(gradient5, is(Arrays.asList(10.0, 0.0, -9.0, 0.0, 0.0))); // f'(x) = 10x^4 - 9x^2
    }

    @Test
    public void getDifferential() {
        // given
        Polynomial polynomial1 = new Polynomial(1.0, 1.0, 0.0); // f(x) = x^2 + x
        Polynomial polynomial2 = spy(new Polynomial(10.0, 3.0)); // f(x) = 10x + 3
        Polynomial polynomial3 = spy(new Polynomial(-4.0)); // f(x) = -4
        Polynomial polynomial4 = new Polynomial(1.0, 5.0, 1.0, 1.0); // f(x) = x^3 + 5x^2 + x + 1

        // when
        double differential1 = polynomial1.getDifferential(1.0);
        double differential2 = polynomial2.getDifferential(anyDouble());
        double differential3 = polynomial3.getDifferential(anyDouble());
        double differential4 = polynomial4.getDifferential(1.0);

        // then
        Assert.assertThat(differential1, closeTo(3.0, IBM_FLOAT_SURROUNDING));
        Assert.assertThat(differential2, closeTo(0.0, IBM_FLOAT_SURROUNDING));
        Assert.assertThat(differential3, closeTo(0.0, IBM_FLOAT_SURROUNDING));
        Assert.assertThat(differential4, closeTo(14.0, IBM_FLOAT_SURROUNDING));
    }

    @Test
    public void getRootByNewtonMethod() {
        // given
        final double LOW_PRECISION = 1.0E-4;
        Polynomial polynomial1 = new Polynomial(1.0, 0.0, -612.0); // f(x) = x^2 - 612
        Polynomial polynomial2 = new Polynomial(2.0, -1.0, -3.0, 0.0); // f(x) = 2x^3 - x^2 - 3x
        Polynomial polynomial3 = new Polynomial(1.0, -3.0, -15.0, 5.0); // f(x) = x^3 - 3x^2 - 15x + 5
        Polynomial polynomial4 = spy(new Polynomial(3.0, -9.0, 26.0, -24.0)); // f(x) = x^3 - 9x^2 + 26x - 24
        Polynomial polynomial5 = new Polynomial(-1.0, 0.0, 1.0); // f(x) = -x^2 + 1
        Polynomial polynomial6 = new Polynomial(1.0, 0.0, -2.0, 2.0); // f(x) = x^3 - 2x + 2

        // when
        Double root1 = polynomial1.getRootByNewtonMethod(10.0);
        Double root2a = polynomial2.getRootByNewtonMethod(0.0);
        Double root2b = polynomial2.getRootByNewtonMethod(-2.0);
        Double root2c = polynomial2.getRootByNewtonMethod((3.0 / 2.0) + 9.9E-12);
        Double root3a = polynomial3.getRootByNewtonMethod(-3.0);
        Double root3b = polynomial3.getRootByNewtonMethod(0.0);
        Double root3c = polynomial3.getRootByNewtonMethod(2.92941 + 1.0);
        Double root4a = polynomial4.getRootByNewtonMethod(Double.MAX_VALUE);
        Double root4b = polynomial4.getRootByNewtonMethod(anyDouble());
        Double root5 = polynomial5.getRootByNewtonMethod(0.0);
        Double root6 = polynomial6.getRootByNewtonMethod(0.0);

        // then
        Assert.assertThat(root1, closeTo(6.0 * Math.sqrt(17.0), IBM_FLOAT_SURROUNDING));
        Assert.assertThat(root2a, closeTo(0.0, IBM_FLOAT_SURROUNDING));
        Assert.assertThat(root2b, closeTo(-1.0, IBM_FLOAT_SURROUNDING));
        Assert.assertThat(root2c, closeTo(1.5, IBM_FLOAT_SURROUNDING));
        Assert.assertThat(root3a, closeTo(-2.8588, LOW_PRECISION));
        Assert.assertThat(root3b, closeTo(0.31552, LOW_PRECISION));
        Assert.assertThat(root3c, closeTo(5.5433, LOW_PRECISION));
        Assert.assertNull(root4a);
        assertNotEquals(root4b, anyOf(is(2.0), is(3.0), is(4.0)));
        Assert.assertNull(root5);
        Assert.assertNull(root6);
    }

}

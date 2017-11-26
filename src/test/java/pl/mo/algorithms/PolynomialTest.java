package pl.mo.algorithms;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.closeTo;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.spy;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

public class PolynomialTest {

    private static final double IBM_FLOAT_SURROUNDING = 5.96E-8;

    @Test
    public void ctor() {
        // when
        Polynomial object1 = new Polynomial();
        Polynomial object2 = new Polynomial(null);
        Polynomial object3 = new Polynomial(-0.0);
        Polynomial object4 = new Polynomial(1.0, 3.0);
        Polynomial object5 = new Polynomial(-11.1, 22.2, 123.45);

        // then
        Assert.assertNotNull(object1);
        Assert.assertNotNull(object2);
        Assert.assertNotNull(object3);
        Assert.assertNotNull(object4);
        Assert.assertNotNull(object5);
    }

    @Test
    public void getValue() {
        // given
        Polynomial object1 = spy(new Polynomial());
        Polynomial object2 = spy(new Polynomial(null));
        Polynomial object3 = spy(new Polynomial(0.0));
        Polynomial object4 = spy(new Polynomial(0.0, 1.0));
        Polynomial object5 = spy(new Polynomial(0.0, 1.0, 2.0));

        // when
        Double value1 = object1.getValue(10.0);
        Double value2 = object2.getValue(1.0);
        Double value3 = object3.getValue(Double.MAX_VALUE);
        Double value4 = object4.getValue(Double.MIN_VALUE);
        Double value5 = object5.getValue(0.0);

        // then
        assertThat(value1, closeTo(555.0, IBM_FLOAT_SURROUNDING));
        assertThat(value2, closeTo(-12.0, IBM_FLOAT_SURROUNDING));
        assertThat(value3, closeTo(0.0, IBM_FLOAT_SURROUNDING));
        assertThat(value4, closeTo(1.0, IBM_FLOAT_SURROUNDING));
        assertThat(value5, closeTo(2.0, IBM_FLOAT_SURROUNDING));
    }

    @Test
    public void getNumberOfCalls() {
        // given
        Polynomial object1 = spy(new Polynomial());
        Polynomial object2 = spy(new Polynomial(null));
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
        Assert.assertEquals(1L, object1.getNumberOfCalls());
        Assert.assertEquals(0L, object2.getNumberOfCalls());
        Assert.assertEquals(1L, object3.getNumberOfCalls());
        Assert.assertEquals(1L, object4.getNumberOfCalls());
        Assert.assertEquals(2L, object5.getNumberOfCalls());
    }

    @Test
    public void getGradientCoefficients() {
        // given
        Polynomial polynomial1 = new Polynomial(1.0, 5.0, 1.0, 1.0); // f(x) = x^3 + 5x^2 + x + 1
        Polynomial polynomial2 = new Polynomial(2.0, 0.0, 0.0); // f(x) = 2x^2
        Polynomial polynomial3 = new Polynomial(7.0, 1.0); // f(x) = 7x + 1
        Polynomial polynomial4 = new Polynomial(1.0); // f(x) = 1
        Polynomial polynomial5 = new Polynomial(2.0, 0.0, -3.0, 0.0, 0.0, 4.0); // f(x) = 2x^5 + 3x^3 + 4

        // when
        List<Double> gradient1 = polynomial1.getGradientCoefficients();
        List<Double> gradient2 = polynomial2.getGradientCoefficients();
        List<Double> gradient3 = polynomial3.getGradientCoefficients();
        List<Double> gradient4 = polynomial4.getGradientCoefficients();
        List<Double> gradient5 = polynomial5.getGradientCoefficients();

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

}

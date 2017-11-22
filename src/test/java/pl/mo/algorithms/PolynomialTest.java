package pl.mo.algorithms;

import static org.hamcrest.Matchers.closeTo;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.spy;

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
        Assert.assertThat(value1, closeTo(555.0, IBM_FLOAT_SURROUNDING));
        Assert.assertThat(value2, closeTo(-12.0, IBM_FLOAT_SURROUNDING));
        Assert.assertThat(value3, closeTo(0.0, IBM_FLOAT_SURROUNDING));
        Assert.assertThat(value4, closeTo(1.0, IBM_FLOAT_SURROUNDING));
        Assert.assertThat(value5, closeTo(2.0, IBM_FLOAT_SURROUNDING));
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

}

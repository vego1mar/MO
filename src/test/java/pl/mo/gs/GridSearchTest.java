package pl.mo.gs;

import static org.hamcrest.Matchers.closeTo;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyInt;

import java.util.NoSuchElementException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GridSearchTest {

    private static final double IBM_FLOAT_SURROUNDING = 5.96E-8;
    private GridSearch object;

    @Before
    public void init() {
        // given
        object = new GridSearch();
    }

    @Test
    public void getPolynomialValueTest() {
        // when
        double value1 = object.getPolynomialValue(-3.0);
        double value2 = object.getPolynomialValue(0.0);
        double value3 = object.getPolynomialValue(1.0);

        // then
        Assert.assertThat(value1, closeTo(-4.0, IBM_FLOAT_SURROUNDING));
        Assert.assertThat(value2, closeTo(5.0, IBM_FLOAT_SURROUNDING));
        Assert.assertThat(value3, closeTo(-12.0, IBM_FLOAT_SURROUNDING));
    }

    @Test
    public void getLocalMinimumArgumentTest() {
        // when
        Double argument1 = object.getLocalMinimumArgument(1.0, 10.0, 0.001);
        Double argument2 = object.getLocalMinimumArgument(1.0, 2.0, 0.001);
        Double argument3 = object.getLocalMinimumArgument(10.0, 1.0, 0.001);

        // then
        Assert.assertThat(argument1, closeTo(1.0 + Math.sqrt(6), 1.0E-3));
        Assert.assertNull(argument2);
        Assert.assertNull(argument3);
    }

    @Test
    public void getNumberOfCallsTest() {
        // when
        object.getLocalMinimumArgument(1.0, 10.0, 0.001);
        long no1 = object.getNumberOfCalls();
        object = new GridSearch();
        object.getLocalMinimumArgument(1.0, 10.0, 0.0001);
        long no2 = object.getNumberOfCalls();
        object = new GridSearch();
        object.getLocalMinimumArgument(1.0, 10.0, 0.001, 10);
        long no3 = object.getNumberOfCalls();
        object = new GridSearch();
        object.getLocalMinimumArgument(1.0, 10.0, 0.001, 100);
        long no4 = object.getNumberOfCalls();

        // then
        Assert.assertEquals(4_900, no1);
        Assert.assertEquals(48_992, no2);
        Assert.assertEquals(13_347, no3);
        Assert.assertEquals(10_280, no4);
    }

    @Test(timeout = 1000)
    public void getLocalMinimumArgumentTestRecursive1() {
        // when
        Double argument1 = object.getLocalMinimumArgument(1.0, 4.0, 0.1, 5);
        Double argument2 = object.getLocalMinimumArgument(2.0, 10.0, Math.abs(anyDouble()) + 0.001, 100);

        // then
        final double EXPECTED_VALUE = 1.0 + Math.sqrt(6.0);
        final double ACCEPTABLE_ERROR = 1.0E-2;
        Assert.assertThat(argument1, closeTo(EXPECTED_VALUE, ACCEPTABLE_ERROR));
        Assert.assertThat(argument2, closeTo(EXPECTED_VALUE, ACCEPTABLE_ERROR));
    }

    @Test(expected = IllegalArgumentException.class)
    public void getLocalMinimumArgumentTestRecursive2() {
        // when
        object.getLocalMinimumArgument(anyDouble(), anyDouble() + Math.abs(anyDouble()), Math.abs(anyDouble()), -Math.abs(anyInt()));
    }

    @Test(expected = NoSuchElementException.class)
    public void getLocalMinimumArgumentTestRecursive3() {
        // when
        object.getLocalMinimumArgument(23.1, 1.23, Math.abs(anyDouble()), anyInt() + 1);
    }

}

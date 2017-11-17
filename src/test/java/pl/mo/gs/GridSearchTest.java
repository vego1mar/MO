package pl.mo.gs;

import static org.hamcrest.Matchers.closeTo;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GridSearchTest {

    private static final double IBM_FLOAT_SURROUNDING = 5.96E-8;
    private GridSearch gridSearch;

    @Before
    public void init() {
        // given
        gridSearch = new GridSearch();
    }

    @Test
    public void getPolynomialValueTest() {
        // when
        double value1 = gridSearch.getPolynomialValue(-3.0);
        double value2 = gridSearch.getPolynomialValue(0.0);
        double value3 = gridSearch.getPolynomialValue(1.0);

        // then
        Assert.assertThat(value1, closeTo(-4.0, IBM_FLOAT_SURROUNDING));
        Assert.assertThat(value2, closeTo(5.0, IBM_FLOAT_SURROUNDING));
        Assert.assertThat(value3, closeTo(-12.0, IBM_FLOAT_SURROUNDING));
    }

    @Test
    public void getLocalMinimumArgumentTest() {
        // when
        Double argument1 = gridSearch.getLocalMinimumArgument(1.0, 10.0, 0.001);
        Double argument2 = gridSearch.getLocalMinimumArgument(1.0, 2.0, 0.001);
        Double argument3 = gridSearch.getLocalMinimumArgument(10.0, 1.0, 0.001);

        // then
        Assert.assertThat(argument1, closeTo(1.0 + Math.sqrt(6), 1.0E-3));
        Assert.assertNull(argument2);
        Assert.assertNull(argument3);
    }

    @Test
    public void getNumberOfCallsTest() {
        // when
        gridSearch.getLocalMinimumArgument(1.0, 10.0, 0.001);
        long no1 = gridSearch.getNumberOfCalls();
        gridSearch = new GridSearch();
        gridSearch.getLocalMinimumArgument(1.0, 10.0, 0.0001);
        long no2 = gridSearch.getNumberOfCalls();

        // then
        Assert.assertEquals(4_900, no1);
        Assert.assertEquals(48_992, no2);
    }

}

package pl.mo.algorithms;

import static org.hamcrest.Matchers.closeTo;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.spy;

import java.util.NoSuchElementException;
import org.junit.Assert;
import org.junit.Test;

public class GridSearchTest {

    private GridSearch object = spy(new GridSearch());

    @Test
    public void getLocalMinimumArgument1() {
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
    public void getLocalMinimumArgument2() {
        // when
        Double argument1 = object.getLocalMinimumArgument(1.0, 4.0, 0.1, 5);
        Double argument2 = object.getLocalMinimumArgument(2.0, 10.0, 0.001, 100);

        // then
        final double EXPECTED_VALUE = 1.0 + Math.sqrt(6.0);
        final double ACCEPTABLE_ERROR = 1.0E-2;
        Assert.assertThat(argument1, closeTo(EXPECTED_VALUE, ACCEPTABLE_ERROR));
        Assert.assertThat(argument2, closeTo(EXPECTED_VALUE, ACCEPTABLE_ERROR));
    }

    @Test(expected = IllegalArgumentException.class)
    public void getLocalMinimumArgument3() {
        // when
        object.getLocalMinimumArgument(-Math.abs(anyDouble()), Math.abs(anyDouble()), Math.abs(anyDouble()), -Math.abs(anyInt()));
    }

    @Test(expected = NoSuchElementException.class)
    public void getLocalMinimumArgument4() {
        // when
        object.getLocalMinimumArgument(1.0, -1.0, 0.0, 1);
    }

}

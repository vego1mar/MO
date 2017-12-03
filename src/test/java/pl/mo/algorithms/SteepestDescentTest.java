package pl.mo.algorithms;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.spy;

import org.junit.Assert;
import org.junit.Test;
import pl.mo.general.ReflectionHelper;

public class SteepestDescentTest {

    private static final double IBM_FLOAT_SURROUNDING = 5.96E-8;
    private SteepestDescent object = spy(new SteepestDescent());

    @Test(expected = UnsupportedOperationException.class)
    public void getLocalMinimumArgument1() {
        // when
        object.getLocalMinimumArgument(anyDouble(), anyDouble(), anyDouble());
    }

    @Test
    public void getLocalMinimumArgument2() throws IllegalAccessException {
        // given
        final double LOCAL_MINIMUM = (1.0 / 2.0) + (Math.sqrt(11.0 / 3.0) / 2.0);
        Polynomial function = new Polynomial(2.0, -3.0, -4.0, 0.0); // f(x) = 2x^3 - 3x^2 - 4x
        ReflectionHelper.getField(object.getClass(), "scoreFunction").set(object, function);

        // when
        double result1 = object.getLocalMinimumArgument(0.0, true);
        int result1No = object.getIterationsNo();
        double result2 = object.getLocalMinimumArgument(0.0, false);
        int result2No = object.getIterationsNo();

        // then
        assertThat(result1, closeTo(LOCAL_MINIMUM, IBM_FLOAT_SURROUNDING));
        assertThat(result2, closeTo(LOCAL_MINIMUM, IBM_FLOAT_SURROUNDING));
        Assert.assertTrue(result2No < result1No);
    }

    @Test
    public void getLocalMinimumArgument3() throws IllegalAccessException {
        // given
        final double LOCAL_MINIMUM = 1.0 + Math.sqrt(6.0);
        Polynomial function = new Polynomial(); // f(x) = x^3 - 3x^2 - 15x + 5
        ReflectionHelper.getField(object.getClass(), "scoreFunction").set(object, function);

        // when
        double result1 = object.getLocalMinimumArgument(0.0, true);
        int result1No = object.getIterationsNo();
        double result2 = object.getLocalMinimumArgument(0.0, false);
        int result2No = object.getIterationsNo();

        // then
        Assert.assertThat(result1, closeTo(LOCAL_MINIMUM, IBM_FLOAT_SURROUNDING));
        Assert.assertThat(result2, closeTo(LOCAL_MINIMUM, IBM_FLOAT_SURROUNDING));
        Assert.assertTrue(result2No < result1No);
    }

    @Test
    public void getLocalMinimumArgument4() throws IllegalAccessException {
        // given
        final double LOCAL_MINIMUM = (7.0 * Math.sqrt(11.0 / 3.0)) / 3.0;
        Polynomial function = new Polynomial(1.0, -((14.0 / 3.0) * Math.sqrt(11.0 / 3.0)), 121.0 / 9.0); // f(x) = x^2 - (14/3)√(11/3)x + (121/9)
        ReflectionHelper.getField(object.getClass(), "scoreFunction").set(object, function);

        // when
        double result1 = object.getLocalMinimumArgument(0.0, true);
        int result1No = object.getIterationsNo();
        double result2 = object.getLocalMinimumArgument(0.0, false);
        int result2No = object.getIterationsNo();

        // then
        Assert.assertThat(result1, closeTo(LOCAL_MINIMUM, IBM_FLOAT_SURROUNDING));
        Assert.assertThat(result2, closeTo(LOCAL_MINIMUM, IBM_FLOAT_SURROUNDING));
        Assert.assertTrue(result2No < result1No);
    }

}

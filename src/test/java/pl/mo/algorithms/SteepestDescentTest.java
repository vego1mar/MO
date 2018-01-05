package pl.mo.algorithms;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.spy;

import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import pl.mo.general.ReflectionHelper;
import pl.mo.tests.Assertions;

public class SteepestDescentTest {

    private SteepestDescent object = spy(new SteepestDescent());

    @Test
    public void getLocalMinimumArgument2() throws IllegalAccessException {
        // given
        final double LOCAL_MINIMUM = (1.0 / 2.0) + (Math.sqrt(11.0 / 3.0) / 2.0);
        Polynomial function = new Polynomial(2.0, -3.0, -4.0, 0.0); // f(x) = 2x^3 - 3x^2 - 4x
        ReflectionHelper.getField(object.getClass(), "objectiveFunction").set(object, function);

        // when
        double result1 = object.getLocalMinimumArgument(0.0, true);
        int result1No = object.getIterationsNo();
        double result2 = object.getLocalMinimumArgument(0.0, false);
        int result2No = object.getIterationsNo();

        // then
        assertThat(result1, closeTo(LOCAL_MINIMUM, Assertions.IBM_FLOAT_SURROUNDING));
        assertThat(result2, closeTo(LOCAL_MINIMUM, Assertions.IBM_FLOAT_SURROUNDING));
        Assert.assertTrue(result2No < result1No);
    }

    @Test
    public void getLocalMinimumArgument3() throws IllegalAccessException {
        // given
        final double LOCAL_MINIMUM = 1.0 + Math.sqrt(6.0);
        Polynomial function = new Polynomial(); // f(x) = x^3 - 3x^2 - 15x + 5
        ReflectionHelper.getField(object.getClass(), "objectiveFunction").set(object, function);

        // when
        double result1 = object.getLocalMinimumArgument(0.0, true);
        int result1No = object.getIterationsNo();
        double result2 = object.getLocalMinimumArgument(0.0, false);
        int result2No = object.getIterationsNo();

        // then
        Assert.assertThat(result1, closeTo(LOCAL_MINIMUM, Assertions.IBM_FLOAT_SURROUNDING));
        Assert.assertThat(result2, closeTo(LOCAL_MINIMUM, Assertions.IBM_FLOAT_SURROUNDING));
        Assert.assertTrue(result2No < result1No);
    }

    @Test
    public void getLocalMinimumArgument4() throws IllegalAccessException {
        // given
        final double LOCAL_MINIMUM = (7.0 * Math.sqrt(11.0 / 3.0)) / 3.0;
        Polynomial function = new Polynomial(1.0, -((14.0 / 3.0) * Math.sqrt(11.0 / 3.0)), 121.0 / 9.0); // f(x) = x^2 - (14/3)√(11/3)x + (121/9)
        ReflectionHelper.getField(object.getClass(), "objectiveFunction").set(object, function);

        // when
        double result1 = object.getLocalMinimumArgument(0.0, true);
        int result1No = object.getIterationsNo();
        double result2 = object.getLocalMinimumArgument(0.0, false);
        int result2No = object.getIterationsNo();

        // then
        Assert.assertThat(result1, closeTo(LOCAL_MINIMUM, Assertions.IBM_FLOAT_SURROUNDING));
        Assert.assertThat(result2, closeTo(LOCAL_MINIMUM, Assertions.IBM_FLOAT_SURROUNDING));
        Assert.assertTrue(result2No < result1No);
    }

    @Test
    public void getLocalMinimumArgument5() throws IllegalAccessException {
        // given
        Paraboloid paraboloid = new Paraboloid();
        ReflectionHelper.getField(object.getClass(), "objectiveFunction").set(object, paraboloid);

        // when
        List<Double> localMinimum1 = object.getLocalMinimumArgument(Arrays.asList(5.0, 5.0), false);
        List<Double> localMinimum2 = object.getLocalMinimumArgument(Arrays.asList(5.0, 5.0), true);

        // then
        Assertions.assertDoubles(localMinimum1, Arrays.asList(2.0, 1.0), 0.1E-3);
        Assertions.assertValues(localMinimum2, Arrays.asList(2.0, 1.0), Double.class);
    }

    @Test
    public void getLocalMinimumArgument6() throws IllegalAccessException {
        // given
        final double A = Math.sqrt(2.0 / 7.0);
        final double F = -2.0 / 3.0;
        final double B = Math.sqrt(7.0 / 2.0);
        final double G = 3.0 / 2.0;
        final double C = Math.log(3.0 * Math.sqrt(7.0));
        Paraboloid paraboloid = new Paraboloid(A,1.0, F, B,1.0, G,C);
        ReflectionHelper.getField(object.getClass(), "objectiveFunction").set(object, paraboloid);
        final List<Double> expectedLocalMinimum = Arrays.asList(2.0 / 3.0, -3.0 / 2.0);

        // when
        List<Double> localMinimum1 = object.getLocalMinimumArgument(expectedLocalMinimum, false);
        List<Double> localMinimum2 = object.getLocalMinimumArgument(expectedLocalMinimum, true);

        // then
        Assertions.assertValues(localMinimum1, expectedLocalMinimum, Double.class);
        Assertions.assertValues(localMinimum2, expectedLocalMinimum, Double.class);
    }

}

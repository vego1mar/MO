package pl.mo.algorithms;

import static org.hamcrest.Matchers.closeTo;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.spy;

import org.junit.Assert;
import org.junit.Test;
import pl.mo.general.ReflectionHelper;

public class SteepestDescentTest {

    private SteepestDescent object = spy(new SteepestDescent());

    @Test
    public void getLocalMinimumArgument1() {
        // when
        Double value = object.getLocalMinimumArgument(anyDouble(), anyDouble(), anyDouble());

        // then
        Assert.assertNull(value);
    }

    @Test
    public void getLocalMinimumArgument2() throws IllegalAccessException {
        // given
        Polynomial function = new Polynomial(3.0, 5.0, 1.0, 1.0);
        ReflectionHelper.getField(object.getClass(), "scoreFunction").set(object, function);

        // when
        double result = object.getLocalMinimumArgument(0.0);

        // then
        Assert.assertThat(result, closeTo(((Math.sqrt(22.0) - 5.0) / 3.0), SteepestDescent.ABSOLUTE_TOLERANCE));
    }

    @Test
    public void getLocalMinimumArgument3() throws IllegalAccessException {
        // given
        Polynomial function = new Polynomial(3.0, -5.0, 2.0, 1.0);
        ReflectionHelper.getField(object.getClass(), "scoreFunction").set(object, function);

        // when
        double result = object.getLocalMinimumArgument(3.0);

        // then
        Assert.assertThat(result, closeTo(((5.0 + Math.sqrt(19.0)) / 3.0), SteepestDescent.ABSOLUTE_TOLERANCE));
    }

}

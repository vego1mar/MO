package pl.mo.algorithms;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.mockito.Mockito.spy;

import java.util.Collections;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import pl.mo.functions.Paraboloid;

public class SimulatedAnnealingTest {

    private SimulatedAnnealing function = spy(new SimulatedAnnealing());

    @Test(timeout = 10_000)
    public void getLocalMinimumArgument1() {
        // given
        List<Double> point2 = List.of(1.0, 3.0);
        SimulatedAnnealing.IntervalConstraints bounds2 = function.new IntervalConstraints();
        bounds2.setX(0.0, 4.0);
        bounds2.setY(-1.0, 3.5);

        SimulatedAnnealing.IntervalConstraints bounds3 = function.new IntervalConstraints();
        bounds3.setX(-5.0, 5.0);
        bounds3.setY(-5.0, 5.0);

        // when
        List<Double> result1 = function.getLocalMinimumArgument(null, null);
        List<Double> result2 = function.getLocalMinimumArgument(point2, bounds2);

        function.setObjectiveFunction(new Paraboloid());
        List<Double> result3 = function.getLocalMinimumArgument(Collections.emptyList(), bounds3);

        // then
        Assert.assertTrue((result1.get(0) > -1.0 && result1.get(0) < 1.0) && (result1.get(1) > -1.0 && result1.get(1) < 1.0));
        assertThat(result2.get(0), closeTo(3.0, 1.0));
        assertThat(result2.get(1), closeTo(2.0, 1.0));
        assertThat(result3.get(0), closeTo(2.0, 1.0E-2));
        assertThat(result3.get(1), closeTo(1.0, 1.0E-2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void getLocalMinimumArgument2() {
        // when
        function.getLocalMinimumArgument(Collections.singletonList(0.0), null);
    }

}

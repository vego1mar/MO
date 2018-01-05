package pl.mo.algorithms;

import static org.mockito.Mockito.spy;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.Test;
import pl.mo.functions.Paraboloid;
import pl.mo.tests.Assertions;

public class HookeJeevesPatternSearchTest {

    private HookeJeevesPatternSearch function = spy(new HookeJeevesPatternSearch());

    @Test
    public void getLocalMinimumArgument() {
        // given
        final List<Double> LOCAL_MINIMUM_1 = List.of(3.0, 2.0);
        final List<Double> LOCAL_MINIMUM_2 = List.of(-3.779310, -3.283186);
        final List<Double> LOCAL_MINIMUM_3 = List.of(2.0, 1.0);

        // when
        List<Double> result1 = function.getLocalMinimumArgument(null);
        List<Double> result2 = function.getLocalMinimumArgument(Collections.emptyList());
        List<Double> result3 = function.getLocalMinimumArgument(Arrays.asList(5.0, 5.0));
        List<Double> result4 = function.getLocalMinimumArgument(Arrays.asList(-Math.sqrt(5.0), -3.0));

        function.setObjectiveFunction(new Paraboloid());
        List<Double> result5 = function.getLocalMinimumArgument(Arrays.asList(0.0, 0.0));
        List<Double> result6 = function.getLocalMinimumArgument(Arrays.asList(5.0, 5.0));

        // then
        Assertions.assertDoubles(result1, LOCAL_MINIMUM_1);
        Assertions.assertDoubles(result2, LOCAL_MINIMUM_1);
        Assertions.assertDoubles(result3, LOCAL_MINIMUM_1);
        Assertions.assertDoubles(result4, LOCAL_MINIMUM_2, 1.0E-3);
        Assertions.assertDoubles(result5, LOCAL_MINIMUM_3);
        Assertions.assertDoubles(result6, LOCAL_MINIMUM_3);
    }

}

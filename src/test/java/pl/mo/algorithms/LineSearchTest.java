package pl.mo.algorithms;

import static org.hamcrest.Matchers.closeTo;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import pl.mo.general.Derivative;
import pl.mo.general.Vectors;
import pl.mo.tests.Assertions;

public class LineSearchTest {

    @Test
    public void performBacktracking1() {
        // given
        Polynomial polynomial1 = new Polynomial(4.0, 0.0, -2.0, 0.0, 1.0); // p(x) = 4x^4 - 2x^2 + 1

        // when
        double minimizer = LineSearch.performBacktracking(polynomial1, 0.0, -polynomial1.getDifferential(0.6));

        // then
        assertThat(minimizer, closeTo(0.25, Derivative.IBM_FLOAT_SURROUNDING));
    }

    @Test
    public void performBacktracking2() {
        // given
        Paraboloid paraboloid = new Paraboloid(); // f(x,y) = 2(x-2)^2 + (y-1)^2 + 3
        final List<Double> point1 = Arrays.asList(5.0, 5.0);
        final List<Double> direction1 = Vectors.negate(point1);
        final List<Double> point2 = Arrays.asList(1.1, -1.1);
        final List<Double> direction2 = Vectors.negate(point2);

        // when
        double minimizer1 = LineSearch.performBacktracking(paraboloid, point1, direction1);
        double minimizer2 = LineSearch.performBacktracking(paraboloid, point2, direction2);

        // then
        assertThat(minimizer1, closeTo(1.0 / 4.0, Assertions.IBM_FLOAT_SURROUNDING));
        assertThat(minimizer2, closeTo(1.0 / 32.0, Assertions.IBM_FLOAT_SURROUNDING));
    }

}

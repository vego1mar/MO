package pl.mo.algorithms;

import static org.hamcrest.Matchers.closeTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import pl.mo.general.Derivative;

public class LineSearchTest {

    @Test
    public void performBacktracking() {
        // given
        Polynomial polynomial1 = new Polynomial(4.0, 0.0, -2.0, 0.0, 1.0); // p(x) = 4x^4 - 2x^2 + 1

        // when
        double minimizer = LineSearch.performBacktracking(polynomial1, 0.0, -polynomial1.getDifferential(0.6));

        // then
        assertThat(minimizer, closeTo(0.25, Derivative.IBM_FLOAT_SURROUNDING));
    }

}

package pl.mo.algorithms;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.junit.Assert.assertThat;

import org.apache.log4j.Logger;
import org.junit.Test;

public class LineSearchTest {

    private static final Logger log = Logger.getLogger(LineSearchTest.class);

    @Test
    public void performBacktracking() {
        // given
        Polynomial polynomial1 = new Polynomial(4.0, 0.0, -2.0, 0.0, 1.0); // p(x) = 4x^4 - 2x^2 + 1

        // when
        double minimizer1a = LineSearch.performBacktracking(polynomial1, 1.0 / 20.0, 1.0 / 3.0); // f(1/20 + 1/3 p)
        double minimizer1b = LineSearch.performBacktracking(polynomial1, -1.0, 2.0); // f(-1 + 2 p)
        double minimizer1c = LineSearch.performBacktracking(polynomial1, 1.0 / 10.0, 1.0); // f(1/10 + 1 p)

        // debug
        log.debug("minimizer1a = " + minimizer1a);
        log.debug("minimizer1b = " + minimizer1b);
        log.debug("minimizer1c = " + minimizer1c);

        // then
        assertThat(minimizer1a, allOf(greaterThanOrEqualTo(1.0 / 20.0), lessThanOrEqualTo((1.0 / 20.0) + (1.0 / 3.0))));
        assertThat(minimizer1b, allOf(greaterThanOrEqualTo(-1.0), lessThanOrEqualTo(2.0)));
        assertThat(minimizer1c, allOf(greaterThanOrEqualTo(1.0 / 10.0), lessThanOrEqualTo(1.0 + (1.0 / 10.0))));
    }

}

package pl.mo.general;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;

import org.junit.Test;

public class PowerTest {

    private static final double IBM_FLOAT_SURROUNDING = 5.96E-8;

    @Test
    public void getFast() {
        // when
        double power1 = Power.getFast(2.0, 4);
        double power2 = Power.getFast(Math.log(23.4), 0);
        double power3 = Power.getFast(-1.23, 1);
        double power4 = Power.getFast((14.0 / 3.0) * Math.sqrt(11.0 / 3.0), 17);
        double power5 = Power.getFast(1.23, 11);

        // then
        assertThat(power1, closeTo(16.0, IBM_FLOAT_SURROUNDING));
        assertThat(power2, closeTo(1.0, IBM_FLOAT_SURROUNDING));
        assertThat(power3, closeTo(-1.23, IBM_FLOAT_SURROUNDING));
        assertThat(power4, closeTo(1.4771426404150671E16, 2.0));
        assertThat(power5, closeTo(9.748913698143826, IBM_FLOAT_SURROUNDING));
    }

}

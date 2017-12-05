package pl.mo.algorithms;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;
import static org.mockito.Mockito.spy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import pl.mo.general.Derivative;

public class ParaboloidTest {

    @Test
    public void getParameterA() {
        // given
        Paraboloid paraboloid1 = spy(new Paraboloid());
        Paraboloid paraboloid2 = spy(new Paraboloid(1.11, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0));
        Paraboloid paraboloid3 = spy(new Paraboloid(7, 6, 5, 4, 3, 2, 1));

        // when
        Integer parameter1A = paraboloid1.getParameterA(Integer.class);
        Double parameter2A = paraboloid2.getParameterA(Double.class);
        Number parameter3A = paraboloid3.getParameterA(Number.class);

        // then
        Assert.assertNotNull(parameter1A);
        Assert.assertNotNull(parameter2A);
        Assert.assertNotNull(parameter3A);
    }

    @Test
    public void getParameterD() {
        // given
        Paraboloid paraboloid1 = spy(new Paraboloid());
        Paraboloid paraboloid2 = spy(new Paraboloid(0.0, 11.1, 0.0, 0.0, 0.0, 0.0, 0.0));
        Paraboloid paraboloid3 = spy(new Paraboloid(7, 6, 5, 4, 3, 2, 1));

        // when
        Integer parameter1D = paraboloid1.getParameterD(Integer.class);
        Double parameter2D = paraboloid2.getParameterD(Double.class);
        Number parameter3D = paraboloid3.getParameterD(Number.class);

        // then
        Assert.assertNotNull(parameter1D);
        Assert.assertNotNull(parameter2D);
        Assert.assertNotNull(parameter3D);
    }

    @Test
    public void getParameterF() {
        // given
        Paraboloid paraboloid1 = spy(new Paraboloid());
        Paraboloid paraboloid2 = spy(new Paraboloid(0.0, 0.0, 11.1, 0.0, 0.0, 0.0, 0.0));
        Paraboloid paraboloid3 = spy(new Paraboloid(7, 6, 5, 4, 3, 2, 1));

        // when
        Integer parameter1F = paraboloid1.getParameterF(Integer.class);
        Double parameter2F = paraboloid2.getParameterF(Double.class);
        Number parameter3F = paraboloid3.getParameterF(Number.class);

        // then
        Assert.assertNotNull(parameter1F);
        Assert.assertNotNull(parameter2F);
        Assert.assertNotNull(parameter3F);
    }

    @Test
    public void getParameterB() {
        // given
        Paraboloid paraboloid1 = spy(new Paraboloid());
        Paraboloid paraboloid2 = spy(new Paraboloid(0.0, 0.0, 0.0, 11.1, 0.0, 0.0, 0.0));
        Paraboloid paraboloid3 = spy(new Paraboloid(7, 6, 5, 4, 3, 2, 1));

        // when
        Integer parameter1B = paraboloid1.getParameterB(Integer.class);
        Double parameter2B = paraboloid2.getParameterB(Double.class);
        Number parameter3B = paraboloid3.getParameterB(Number.class);

        // then
        Assert.assertNotNull(parameter1B);
        Assert.assertNotNull(parameter2B);
        Assert.assertNotNull(parameter3B);
    }

    @Test
    public void getParameterE() {
        // given
        Paraboloid paraboloid1 = spy(new Paraboloid());
        Paraboloid paraboloid2 = spy(new Paraboloid(0.0, 0.0, 0.0, 0.0, 1.11, 0.0, 0.0));
        Paraboloid paraboloid3 = spy(new Paraboloid(7, 6, 5, 4, 3, 2, 1));

        // when
        Integer parameter1E = paraboloid1.getParameterE(Integer.class);
        Double parameter2E = paraboloid2.getParameterE(Double.class);
        Number parameter3E = paraboloid3.getParameterE(Number.class);

        // then
        Assert.assertNotNull(parameter1E);
        Assert.assertNotNull(parameter2E);
        Assert.assertNotNull(parameter3E);
    }

    @Test
    public void getParameterG() {
        // given
        Paraboloid paraboloid1 = spy(new Paraboloid());
        Paraboloid paraboloid2 = spy(new Paraboloid(0.0, 0.0, 0.0, 0.0, 0.0, 11.1, 0.0));
        Paraboloid paraboloid3 = spy(new Paraboloid(7, 6, 5, 4, 3, 2, 1));

        // when
        Integer parameter1G = paraboloid1.getParameterG(Integer.class);
        Double parameter2G = paraboloid2.getParameterG(Double.class);
        Number parameter3G = paraboloid3.getParameterG(Number.class);

        // then
        Assert.assertNotNull(parameter1G);
        Assert.assertNotNull(parameter2G);
        Assert.assertNotNull(parameter3G);
    }

    @Test
    public void getParameterC() {
        // given
        Paraboloid paraboloid1 = spy(new Paraboloid());
        Paraboloid paraboloid2 = spy(new Paraboloid(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 11.1));
        Paraboloid paraboloid3 = spy(new Paraboloid(7, 6, 5, 4, 3, 2, 1));

        // when
        Integer parameter1C = paraboloid1.getParameterC(Integer.class);
        Double parameter2C = paraboloid2.getParameterC(Double.class);
        Number parameter3C = paraboloid3.getParameterC(Number.class);

        // then
        Assert.assertNotNull(parameter1C);
        Assert.assertNotNull(parameter2C);
        Assert.assertNotNull(parameter3C);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void getValue1() {
        // when
        new Paraboloid().getValue(0.0);
    }

    @Test
    public void getValue2() {
        // given
        Paraboloid paraboloid1 = new Paraboloid(); // f(x,y) = 2(x-2)^2 + (y-1)^2 + 3
        Paraboloid paraboloid2 = new Paraboloid(2, 1, -3, -1, 1, 1, 0); // f(x,y) = 2(x-3)^2 - (y+1)^2
        Paraboloid paraboloid3 = new Paraboloid(0.0, 0.1, 0.2, -1.0, Math.sqrt(7.0), -10.0 / 3.0, 121.0 / 7.0); // f(x,y) = -(y√7 - 10/3)^2 + 121/7

        // when
        Number value1 = paraboloid1.getValue(0, 0);
        Number value2 = paraboloid2.getValue(0, -1);
        Number value3 = paraboloid3.getValue(11.0 / 3.0, 3.0 / 11.0);

        // then
        Assert.assertTrue(value1.intValue() == 12);
        Assert.assertTrue(value2.intValue() == 18);
        assertThat(value3.doubleValue(), closeTo((43_100.0 + (13_860 * Math.sqrt(7.0))) / 7623.0, Derivative.IBM_FLOAT_SURROUNDING));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void getDifferential1() {
        // when
        new Paraboloid().getDifferential(0.0);
    }

    @Test
    public void getDifferential() {
        // given
        Paraboloid paraboloid1 = new Paraboloid(); // f(x,y) = 2(x-2)^2 + (y-1)^2 + 3
        Paraboloid paraboloid2 = new Paraboloid(1.0, 1.0, -5.0, -11.0 / 3.0, 2.0 * Math.sqrt(2.0), -5.0 / 4.0, Math.log(Math.sqrt(4.0 / 3.0)));
        // f(x,y) = (x-5)^2 -11/3((2√2)y - 5/4)^2 + ln(√(4/3))
        final Number X2 = -10.0;
        final Number Y2 = -(((44.0 * Math.sqrt(2.0)) / 3.0) - (55.0 / 6.0));
        final List<Number> EXPECTED_2 = Arrays.asList(X2, Y2);

        // when
        List<Number> gradient1 = paraboloid1.getDifferential(5, 5);
        List<Number> gradient2 = paraboloid2.getDifferential(0.0, 1.0);

        // then
        Assert.assertArrayEquals(new ArrayList<Number>(Arrays.asList(12, 8)).toArray(), gradient1.toArray());

        for (int i = 0; i < gradient2.size(); i++) {
            assertThat(gradient2.get(i).doubleValue(), closeTo(EXPECTED_2.get(i).doubleValue(), Derivative.IBM_FLOAT_SURROUNDING));
        }
    }

}

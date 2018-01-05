package pl.mo.functions;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;
import static org.mockito.Mockito.spy;

import org.junit.Assert;
import org.junit.Test;
import pl.mo.tests.Assertions;

public class HimmelblauTest {

    private final Himmelblau function = spy(new Himmelblau());

    @Test(expected = UnsupportedOperationException.class)
    public void getValue1() {
        // when
        function.getValue(0.0);
    }

    @Test
    public void getValue2() {
        // when
        Number result1 = function.getValue(null, 0.0);
        Number result2 = function.getValue(0.0, null);
        Number result3 = function.getValue(null, null);

        // then
        Assert.assertNull(result1);
        Assert.assertNull(result2);
        Assert.assertNull(result3);
    }

    @Test
    public void getValue3() {
        // when
        Number result1 = function.getValue(-1.0 / 2.0, 0.0);
        Number result2 = function.getValue(0.0, 0.0);
        Number result3 = function.getValue(1.0 / 2.0, 0.0);
        Number result4 = function.getValue(1.0 / 2.0, 1.0 / 2.0);
        Number result5 = function.getValue(3.0, 2.0);
        Number result6 = function.getValue(-2.805118, 3.131312);
        Number result7 = function.getValue(-3.779310, -3.283186);
        Number result8 = function.getValue(3.584428, -1.848126);
        Number result9 = function.getValue(-0.270845, -0.923039);

        // then
        assertThat(result1.doubleValue(), closeTo(171.0 + (13.0 / 16.0), Assertions.IBM_FLOAT_SURROUNDING));
        assertThat(result2.doubleValue(), closeTo(170.0, Assertions.IBM_FLOAT_SURROUNDING));
        assertThat(result3.doubleValue(), closeTo(157.0 + (13.0 / 16.0), Assertions.IBM_FLOAT_SURROUNDING));
        assertThat(result4.doubleValue(), closeTo(144.0 + (1.0 / 8.0), Assertions.IBM_FLOAT_SURROUNDING));
        assertThat(result5.doubleValue(), closeTo(0.0, Assertions.IBM_FLOAT_SURROUNDING));
        assertThat(result6.doubleValue(), closeTo(0.0, Assertions.IBM_FLOAT_SURROUNDING));
        assertThat(result7.doubleValue(), closeTo(0.0, Assertions.IBM_FLOAT_SURROUNDING));
        assertThat(result8.doubleValue(), closeTo(0.0, Assertions.IBM_FLOAT_SURROUNDING));
        assertThat(result9.doubleValue(), closeTo(181.617, 1.0E-3));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void getDifferential1() {
        // when
        function.getDifferential(0.0);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void getDifferential2() {
        // when
        function.getDifferential(0.0, 0.0);
    }

}

package pl.mo.algorithms;

import static org.hamcrest.Matchers.closeTo;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.spy;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class GoldenSectionSearchTest {

    private GoldenSectionSearch gss = spy(new GoldenSectionSearch());

    @Test
    public void getLocalMinimumArgument1() {
        // when
        Double value = gss.getLocalMinimumArgument(1.0, 10.0, 0.001);

        // then
        Assert.assertThat(value, closeTo(1.0 + Math.sqrt(6.0), 1.0E-3));
    }

    @Test(expected = IllegalArgumentException.class)
    public void getLocalMinimumArgumentTest2() {
        // when
        gss.getLocalMinimumArgument(anyDouble(), anyDouble(), anyDouble());
    }

}

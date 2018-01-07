package pl.mo.general;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.number.IsCloseTo.closeTo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import org.junit.Assert;
import org.junit.Test;
import pl.mo.tests.Assertions;

public class NumbersTest {

    @Test
    public void swap() {
        // given
        final double DOUBLE_1 = 0.0;
        final double DOUBLE_2 = 1.0;
        final double FLOAT_1 = 2.0f;
        final double FLOAT_2 = -3.0f;
        final long LONG_1 = 10_000_000_000L;
        final long LONG_2 = 100_000_000_000L;
        final int INTEGER_1 = 99_000;
        final int INTEGER_2 = -99_000;
        final short SHORT_1 = 23_000;
        final short SHORT_2 = -23_000;
        final byte BYTE_1 = 100;
        final byte BYTE_2 = -120;

        // when
        List<Number> result1 = Numbers.swap(DOUBLE_1, DOUBLE_2);
        List<Number> result2 = Numbers.swap(FLOAT_1, FLOAT_2);
        List<Number> result3 = Numbers.swap(LONG_1, LONG_2);
        List<Number> result4 = Numbers.swap(INTEGER_1, INTEGER_2);
        List<Number> result5 = Numbers.swap(SHORT_1, SHORT_2);
        List<Number> result6 = Numbers.swap(BYTE_1, BYTE_2);

        // then
        assertThat(result1.get(0).doubleValue(), closeTo(DOUBLE_2, Assertions.IBM_FLOAT_SURROUNDING));
        assertThat(result1.get(1).doubleValue(), closeTo(DOUBLE_1, Assertions.IBM_FLOAT_SURROUNDING));
        assertThat(result2.get(0).doubleValue(), closeTo(FLOAT_2, Assertions.IBM_FLOAT_SURROUNDING));
        assertThat(result2.get(1).doubleValue(), closeTo(FLOAT_1, Assertions.IBM_FLOAT_SURROUNDING));
        Assert.assertTrue(result3.get(0).longValue() == LONG_2 && result3.get(1).longValue() == LONG_1);
        Assert.assertTrue(result4.get(0).intValue() == INTEGER_2 && result4.get(1).intValue() == INTEGER_1);
        Assert.assertTrue(result5.get(0).shortValue() == SHORT_2 && result5.get(1).shortValue() == SHORT_1);
        Assert.assertTrue(result6.get(0).byteValue() == BYTE_2 && result6.get(1).byteValue() == BYTE_1);
    }

    @Test
    public void isInRange() {
        // given
        final byte BYTE_LEFT = (byte) -120;
        final byte BYTE_RIGHT = (byte) 121;
        final short SHORT_LEFT = (short) -32_000;
        final short SHORT_RIGHT = (short) 31_999;
        final int INTEGER_LEFT = -100_000;
        final int INTEGER_RIGHT = 1_000_000;
        final long LONG_LEFT = -10_000_000_000L;
        final long LONG_RIGHT = 23_000_000_111L;
        final float FLOAT_LEFT = 3.0f / 7.0f;
        final float FLOAT_RIGHT = 4.0f / 7.0f;
        final double DOUBLE_LEFT = Math.sqrt(5.0 / 11.0);
        final double DOUBLE_RIGHT = Math.sqrt(9.0 / 11.0);

        // when
        boolean result1 = Numbers.isInRange((byte) -121, BYTE_LEFT, BYTE_RIGHT);
        boolean result2 = Numbers.isInRange((byte) 0, BYTE_LEFT, BYTE_RIGHT);
        boolean result3 = Numbers.isInRange((byte) 122, BYTE_LEFT, BYTE_RIGHT);
        boolean result4 = Numbers.isInRange((short) -32_001, SHORT_LEFT, SHORT_RIGHT);
        boolean result5 = Numbers.isInRange((short) 0, SHORT_LEFT, SHORT_RIGHT);
        boolean result6 = Numbers.isInRange((short) 32_000, SHORT_LEFT, SHORT_RIGHT);
        boolean result7 = Numbers.isInRange(-100_001, INTEGER_LEFT, INTEGER_RIGHT);
        boolean result8 = Numbers.isInRange(0, INTEGER_LEFT, INTEGER_RIGHT);
        boolean result9 = Numbers.isInRange(1_000_001, INTEGER_LEFT, INTEGER_RIGHT);
        boolean result10 = Numbers.isInRange(-10_000_000_001L, LONG_LEFT, LONG_RIGHT);
        boolean result11 = Numbers.isInRange(0, LONG_LEFT, LONG_RIGHT);
        boolean result12 = Numbers.isInRange(23_000_000_112L, LONG_LEFT, LONG_RIGHT);
        boolean result13 = Numbers.isInRange(2.0f / 7.0f, FLOAT_LEFT, FLOAT_RIGHT);
        boolean result14 = Numbers.isInRange((3.0f / 7.0f) + ((1.0f / 7.0f) / 2.0f), FLOAT_LEFT, FLOAT_RIGHT);
        boolean result15 = Numbers.isInRange(5.0f / 7.0f, FLOAT_LEFT, FLOAT_RIGHT);
        boolean result16 = Numbers.isInRange(Math.sqrt(4.0 / 11.0), DOUBLE_LEFT, DOUBLE_RIGHT);
        boolean result17 = Numbers.isInRange(Math.sqrt(7.0 / 11.0), DOUBLE_LEFT, DOUBLE_RIGHT);
        boolean result18 = Numbers.isInRange(Math.sqrt(10.0 / 11.0), DOUBLE_LEFT, DOUBLE_RIGHT);

        boolean result19 = Numbers.isInRange(100, INTEGER_LEFT, INTEGER_RIGHT);
        boolean result20 = Numbers.isInRange(100, INTEGER_RIGHT, INTEGER_LEFT);

        // then
        Assert.assertFalse(result1);
        Assert.assertTrue(result2);
        Assert.assertFalse(result3);
        Assert.assertFalse(result4);
        Assert.assertTrue(result5);
        Assert.assertFalse(result6);
        Assert.assertFalse(result7);
        Assert.assertTrue(result8);
        Assert.assertFalse(result9);
        Assert.assertFalse(result10);
        Assert.assertTrue(result11);
        Assert.assertFalse(result12);
        Assert.assertFalse(result13);
        Assert.assertTrue(result14);
        Assert.assertFalse(result15);
        Assert.assertFalse(result16);
        Assert.assertTrue(result17);
        Assert.assertFalse(result18);

        Assert.assertTrue(result19);
        Assert.assertTrue(result20);
    }

    @Test
    public void getRandomGaussian() {
        // given
        final byte MIN_1 = (byte) -66;
        final byte MAX_1 = (byte) 123;
        final int MIN_2 = -100;
        final int MAX_2 = 123_456_789;
        final double MIN_3 = new Random(System.nanoTime()).nextDouble();
        final double MAX_3 = new Random(System.currentTimeMillis()).nextDouble() + MIN_3;

        // when
        List<Double> gauss1 = new ArrayList<>();
        List<Double> gauss2 = new ArrayList<>();
        List<Double> gauss3 = new ArrayList<>();

        for (int i = 0; i < 100_000; i++) {
            gauss1.add(Numbers.getRandomGaussian(MIN_1, MAX_1));
            gauss2.add(Numbers.getRandomGaussian(MIN_2, MAX_2));
            gauss3.add(Numbers.getRandomGaussian(MIN_3, MAX_3));
        }

        double min1 = Collections.min(gauss1);
        double max1 = Collections.max(gauss1);
        double min2 = Collections.min(gauss2);
        double max2 = Collections.max(gauss2);
        double min3 = Collections.min(gauss3);
        double max3 = Collections.max(gauss3);

        // then
        Assert.assertTrue((min1 > MIN_1 && min1 < MAX_1) && (max1 > MIN_1 && max1 < MAX_1));
        Assert.assertTrue((min2 > MIN_2 && min2 < MAX_2) && (max2 > MIN_2 && max2 < MAX_2));
        Assert.assertTrue((min3 > MIN_3 && min3 < MAX_3) && (max3 > MIN_3 && max3 < MAX_3));
    }

}

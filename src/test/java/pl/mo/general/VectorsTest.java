package pl.mo.general;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import pl.mo.tests.Assertions;

public class VectorsTest {

    @Test
    public void cast() {
        // given
        final List<Short> SHORTS_LIST = new ArrayList<>(Arrays.asList((short) 1, (short) 0, (short) -3, (short) 4, (short) 121));
        final List<Integer> INTS_LIST = new ArrayList<>(Arrays.asList(1, 0, -3, 4, 121));
        final List<Byte> BYTES_LIST = new ArrayList<>(Arrays.asList((byte) 1, (byte) 0, (byte) -3, (byte) 4, (byte) 121));
        final List<Long> LONGS_LIST = new ArrayList<>(Arrays.asList(1L, 0L, -3L, 4L, 121L));
        final List<Float> FLOATS_LIST = new ArrayList<>(Arrays.asList(1.0f, 0.0f, -3.0f, 4.0f, 121.0f));
        final List<Double> DOUBLES_LIST = new ArrayList<>(Arrays.asList(1.0, 0.0, -3.0, 4.0, 121.0));

        // when
        List<Integer> result1 = Vectors.cast(INTS_LIST, Integer.class);
        List<Short> result2 = Vectors.cast(INTS_LIST, Short.class);
        List<Byte> result3 = Vectors.cast(INTS_LIST, Byte.class);
        List<Long> result4 = Vectors.cast(INTS_LIST, Long.class);
        List<Float> result5 = Vectors.cast(INTS_LIST, Float.class);
        List<Double> result6 = Vectors.cast(INTS_LIST, Double.class);

        List<Integer> result7 = Vectors.cast(SHORTS_LIST, Integer.class);
        List<Short> result8 = Vectors.cast(SHORTS_LIST, Short.class);
        List<Byte> result9 = Vectors.cast(SHORTS_LIST, Byte.class);
        List<Long> result10 = Vectors.cast(SHORTS_LIST, Long.class);
        List<Float> result11 = Vectors.cast(SHORTS_LIST, Float.class);
        List<Double> result12 = Vectors.cast(SHORTS_LIST, Double.class);

        List<Integer> result13 = Vectors.cast(BYTES_LIST, Integer.class);
        List<Short> result14 = Vectors.cast(BYTES_LIST, Short.class);
        List<Byte> result15 = Vectors.cast(BYTES_LIST, Byte.class);
        List<Long> result16 = Vectors.cast(BYTES_LIST, Long.class);
        List<Float> result17 = Vectors.cast(BYTES_LIST, Float.class);
        List<Double> result18 = Vectors.cast(BYTES_LIST, Double.class);

        List<Integer> result19 = Vectors.cast(LONGS_LIST, Integer.class);
        List<Short> result20 = Vectors.cast(LONGS_LIST, Short.class);
        List<Byte> result21 = Vectors.cast(LONGS_LIST, Byte.class);
        List<Long> result22 = Vectors.cast(LONGS_LIST, Long.class);
        List<Float> result23 = Vectors.cast(LONGS_LIST, Float.class);
        List<Double> result24 = Vectors.cast(LONGS_LIST, Double.class);

        List<Integer> result25 = Vectors.cast(FLOATS_LIST, Integer.class);
        List<Short> result26 = Vectors.cast(FLOATS_LIST, Short.class);
        List<Byte> result27 = Vectors.cast(FLOATS_LIST, Byte.class);
        List<Long> result28 = Vectors.cast(FLOATS_LIST, Long.class);
        List<Float> result29 = Vectors.cast(FLOATS_LIST, Float.class);
        List<Double> result30 = Vectors.cast(FLOATS_LIST, Double.class);

        List<Integer> result31 = Vectors.cast(DOUBLES_LIST, Integer.class);
        List<Short> result32 = Vectors.cast(DOUBLES_LIST, Short.class);
        List<Byte> result33 = Vectors.cast(DOUBLES_LIST, Byte.class);
        List<Long> result34 = Vectors.cast(DOUBLES_LIST, Long.class);
        List<Float> result35 = Vectors.cast(DOUBLES_LIST, Float.class);
        List<Double> result36 = Vectors.cast(DOUBLES_LIST, Double.class);

        // then
        Assert.assertNotNull(result1);
        Assert.assertArrayEquals(INTS_LIST.toArray(), result1.toArray());
        Assertions.assertTypes(result1, INTS_LIST);
        Assert.assertNotNull(result2);
        Assert.assertArrayEquals(SHORTS_LIST.toArray(), result2.toArray());
        Assertions.assertTypes(result2, SHORTS_LIST);
        Assert.assertNotNull(result3);
        Assert.assertArrayEquals(BYTES_LIST.toArray(), result3.toArray());
        Assertions.assertTypes(result3, BYTES_LIST);
        Assert.assertNotNull(result4);
        Assert.assertArrayEquals(LONGS_LIST.toArray(), result4.toArray());
        Assertions.assertTypes(result4, LONGS_LIST);
        Assert.assertNotNull(result5);
        Assertions.assertFloats(result5, FLOATS_LIST);
        Assertions.assertTypes(result5, FLOATS_LIST);
        Assert.assertNotNull(result6);
        Assertions.assertDoubles(result6, DOUBLES_LIST);
        Assertions.assertTypes(result6, DOUBLES_LIST);

        Assert.assertNotNull(result7);
        Assert.assertArrayEquals(INTS_LIST.toArray(), result7.toArray());
        Assertions.assertTypes(result7, INTS_LIST);
        Assert.assertNotNull(result8);
        Assert.assertArrayEquals(SHORTS_LIST.toArray(), result8.toArray());
        Assertions.assertTypes(result8, SHORTS_LIST);
        Assert.assertNotNull(result9);
        Assert.assertArrayEquals(BYTES_LIST.toArray(), result9.toArray());
        Assertions.assertTypes(result9, BYTES_LIST);
        Assert.assertNotNull(result10);
        Assert.assertArrayEquals(LONGS_LIST.toArray(), result10.toArray());
        Assertions.assertTypes(result10, LONGS_LIST);
        Assert.assertNotNull(result11);
        Assertions.assertFloats(result11, FLOATS_LIST);
        Assertions.assertTypes(result11, FLOATS_LIST);
        Assert.assertNotNull(result12);
        Assertions.assertDoubles(result12, DOUBLES_LIST);
        Assertions.assertTypes(result12, DOUBLES_LIST);

        Assert.assertNotNull(result13);
        Assert.assertArrayEquals(INTS_LIST.toArray(), result13.toArray());
        Assertions.assertTypes(result13, INTS_LIST);
        Assert.assertNotNull(result14);
        Assert.assertArrayEquals(SHORTS_LIST.toArray(), result14.toArray());
        Assertions.assertTypes(result14, SHORTS_LIST);
        Assert.assertNotNull(result15);
        Assert.assertArrayEquals(BYTES_LIST.toArray(), result15.toArray());
        Assertions.assertTypes(result15, BYTES_LIST);
        Assert.assertNotNull(result16);
        Assert.assertArrayEquals(LONGS_LIST.toArray(), result16.toArray());
        Assertions.assertTypes(result16, LONGS_LIST);
        Assert.assertNotNull(result17);
        Assertions.assertFloats(result17, FLOATS_LIST);
        Assertions.assertTypes(result17, FLOATS_LIST);
        Assert.assertNotNull(result18);
        Assertions.assertDoubles(result18, DOUBLES_LIST);
        Assertions.assertTypes(result18, DOUBLES_LIST);

        Assert.assertNotNull(result19);
        Assert.assertArrayEquals(INTS_LIST.toArray(), result19.toArray());
        Assertions.assertTypes(result19, INTS_LIST);
        Assert.assertNotNull(result20);
        Assert.assertArrayEquals(SHORTS_LIST.toArray(), result20.toArray());
        Assertions.assertTypes(result20, SHORTS_LIST);
        Assert.assertNotNull(result21);
        Assert.assertArrayEquals(BYTES_LIST.toArray(), result21.toArray());
        Assertions.assertTypes(result21, BYTES_LIST);
        Assert.assertNotNull(result22);
        Assert.assertArrayEquals(LONGS_LIST.toArray(), result22.toArray());
        Assertions.assertTypes(result22, LONGS_LIST);
        Assert.assertNotNull(result23);
        Assertions.assertFloats(result23, FLOATS_LIST);
        Assertions.assertTypes(result23, FLOATS_LIST);
        Assert.assertNotNull(result24);
        Assertions.assertDoubles(result24, DOUBLES_LIST);
        Assertions.assertTypes(result24, DOUBLES_LIST);

        Assert.assertTrue(result25.isEmpty());
        Assert.assertTrue(result26.isEmpty());
        Assert.assertTrue(result27.isEmpty());
        Assert.assertTrue(result28.isEmpty());
        Assert.assertNotNull(result29);
        Assertions.assertFloats(result29, FLOATS_LIST);
        Assertions.assertTypes(result29, FLOATS_LIST);
        Assert.assertNotNull(result30);
        Assertions.assertDoubles(result30, DOUBLES_LIST);
        Assertions.assertTypes(result30, DOUBLES_LIST);

        Assert.assertTrue(result31.isEmpty());
        Assert.assertTrue(result32.isEmpty());
        Assert.assertTrue(result33.isEmpty());
        Assert.assertTrue(result34.isEmpty());
        Assert.assertNotNull(result35);
        Assertions.assertFloats(result35, FLOATS_LIST);
        Assertions.assertTypes(result35, FLOATS_LIST);
        Assert.assertNotNull(result36);
        Assertions.assertDoubles(result36, DOUBLES_LIST);
        Assertions.assertTypes(result36, DOUBLES_LIST);
    }

    @Test
    public void getSumOfPowers() {
        // given
        List<Byte> bytes = new ArrayList<>(Arrays.asList((byte) 1, (byte) 2, (byte) 3, (byte) 4, (byte) 5));
        List<Number> empty = Collections.emptyList();
        List<Number> singleton = Collections.singletonList(1);
        List<Short> shorts = new ArrayList<>(Arrays.asList((short) 1, (short) 2, (short) 3, (short) 4, (short) 5));
        List<Integer> integers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        List<Long> longs = new ArrayList<>(Arrays.asList(1L, 2L, 3L, 4L, 5L));
        List<Float> floats = new ArrayList<>(Arrays.asList(11.0f / 3.0f, Float.valueOf(String.valueOf(Math.sqrt(7.0f))), 1.0f, 0.0f));
        final Float RESULT_9 = (161_294.0f / 243.0f) + (49.0f * Float.valueOf(String.valueOf(Math.sqrt(7.0))));
        List<Double> doubles = new ArrayList<>(Arrays.asList(0.0, 0.0));

        // when
        Byte result1 = Vectors.getSumOfPowers(bytes, 2);
        Number result2 = Vectors.getSumOfPowers(empty, 3);
        Number result3 = Vectors.getSumOfPowers(null, 4);
        Number result4 = Vectors.getSumOfPowers(singleton, 0);
        Number result5 = Vectors.getSumOfPowers(singleton, Integer.MIN_VALUE);
        Short result6 = Vectors.getSumOfPowers(shorts, 3);
        Integer result7 = Vectors.getSumOfPowers(integers, 4);
        Long result8 = Vectors.getSumOfPowers(longs, 20);
        Float result9 = Vectors.getSumOfPowers(floats, 5);
        Double result10 = Vectors.getSumOfPowers(doubles, 1);

        // then
        Assert.assertTrue(result1 == 1 + 4 + 9 + 16 + 25);
        Assert.assertNull(result2);
        Assert.assertNull(result3);
        Assert.assertNull(result4);
        Assert.assertNull(result5);
        Assert.assertTrue(result6 == 1 + 8 + 27 + 64 + 125);
        Assert.assertTrue(result7 == 1 + 16 + 81 + 256 + 625);
        Assert.assertTrue(result8 == 1L + 1_048_576L + 3_486_784_401L + 1_099_511_627_776L + 95_367_431_640_625L);
        assertThat(result9.doubleValue(), closeTo(RESULT_9.doubleValue(), 0.001));
        assertThat(result10, closeTo(0.0, Assertions.IBM_FLOAT_SURROUNDING));
    }

    @Test
    public void getNorm() {
        // given
        List<Integer> vector1 = new ArrayList<>(Arrays.asList(1, 2, 1, 3, 2, 4, 7));

        // when
        Double norm1 = Vectors.getNorm(vector1, 2);

        // then
        assertThat(norm1, closeTo(2.0 * Math.sqrt(21.0), Assertions.IBM_FLOAT_SURROUNDING));
    }

}

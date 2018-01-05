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
    public void cast1() {
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
        final double EXPECTED_RESULT = 2.0 * Math.sqrt(21.0);
        List<Integer> vector1 = new ArrayList<>(Arrays.asList(1, 2, 1, 3, 2, 4, 7));
        List<Short> vector2 = new ArrayList<>(Arrays.asList((short) 1, (short) 2, (short) 1, (short) 3, (short) 2, (short) 4, (short) 7));
        List<Byte> vector3 = new ArrayList<>(Arrays.asList((byte) 1, (byte) 2, (byte) 1, (byte) 3, (byte) 2, (byte) 4, (byte) 7));
        List<Long> vector4 = new ArrayList<>(Arrays.asList(1L, 2L, 1L, 3L, 2L, 4L, 7L));
        List<Float> vector5 = new ArrayList<>(Arrays.asList(1.0f, 2.0f, 1.0f, 3.0f, 2.0f, 4.0f, 7.0f));
        List<Double> vector6 = new ArrayList<>(Arrays.asList(1.0, 2.0, 1.0, 3.0, 2.0, 4.0, 7.0));

        // when
        Double norm1 = Vectors.getNorm(vector1, 2);
        Double norm2 = Vectors.getNorm(vector2, 2);
        Double norm3 = Vectors.getNorm(vector3, 2);
        Double norm4 = Vectors.getNorm(vector4, 2);
        Double norm5 = Vectors.getNorm(vector5, 2);
        Double norm6 = Vectors.getNorm(vector6, 2);

        // then
        assertThat(norm1, closeTo(EXPECTED_RESULT, Assertions.IBM_FLOAT_SURROUNDING));
        assertThat(norm2, closeTo(EXPECTED_RESULT, Assertions.IBM_FLOAT_SURROUNDING));
        assertThat(norm3, closeTo(EXPECTED_RESULT, Assertions.IBM_FLOAT_SURROUNDING));
        assertThat(norm4, closeTo(EXPECTED_RESULT, Assertions.IBM_FLOAT_SURROUNDING));
        assertThat(norm5, closeTo(EXPECTED_RESULT, Assertions.IBM_FLOAT_SURROUNDING));
        assertThat(norm6, closeTo(EXPECTED_RESULT, Assertions.IBM_FLOAT_SURROUNDING));
    }

    @Test
    public void negate() {
        // when
        List<Number> result0 = Vectors.negate(Collections.emptyList());
        List<Number> result1 = Vectors.negate(null);
        List<Byte> result2 = Vectors.negate(Collections.singletonList((byte) 23));
        List<Short> result3 = Vectors.negate(Collections.singletonList((short) -23_334));
        List<Integer> result4 = Vectors.negate(Collections.singletonList(739_528));
        List<Long> result5 = Vectors.negate(Collections.singletonList(-739_528_001_002_666L));
        List<Float> result6 = Vectors.negate(Collections.singletonList(1.0f));
        List<Double> result7 = Vectors.negate(Collections.singletonList(-123.456));
        List<Integer> result8 = Vectors.negate(Arrays.asList(1, -1, 0));

        // then
        Assert.assertTrue(result0.isEmpty());
        Assert.assertTrue(result1.isEmpty());
        Assert.assertTrue(result2.get(0) == -23);
        Assert.assertTrue(result3.get(0) == (short) 23_334);
        Assert.assertTrue(result4.get(0) == -739_528);
        Assert.assertTrue(result5.get(0) == 739_528_001_002_666L);
        assertThat(result6.get(0).doubleValue(), closeTo(-1.0, Assertions.IBM_FLOAT_SURROUNDING));
        assertThat(result7.get(0), closeTo(123.456, Assertions.IBM_FLOAT_SURROUNDING));
        Assert.assertArrayEquals(Arrays.asList(-1, 1, 0).toArray(), result8.toArray());
    }

    @Test
    public void multiplyByScalar() {
        // given
        List<Byte> emptyBytes = new ArrayList<>();
        List<Byte> bytes = Arrays.asList((byte) -1, (byte) 0, (byte) 1, (byte) 2, (byte) 3);
        List<Short> shorts = Arrays.asList((short) -100, (short) 0, (short) 10_020);
        List<Integer> integers = Arrays.asList(-123_321, 0, 99_911_102);
        List<Long> longs = Arrays.asList(-767_343_909_110L, 0L, 111_222_333_444_555L);
        List<Float> floats = Arrays.asList(-1.111f, 0.0f, 9.999f);
        List<Double> doubles = Arrays.asList(-0.9999999999, 0.0, 0.9999999999);
        final List<Byte> expectedBytes = Arrays.asList((byte) -2, (byte) 0, (byte) 2, (byte) 4, (byte) 6);
        final List<Short> expectedShorts = Arrays.asList((short) -1000, (short) 0, (short) 100_200);
        final List<Integer> expectedIntegers = Arrays.asList(-369_963, 0, 299_733_306);
        final List<Float> expectedFloats = Arrays.asList(-1.36653f, 0.0f, 12.29877f);
        final List<Double> expectedDoubles = Arrays.asList(0.0, 0.0, 0.0);

        // when
        List<Number> result0 = Vectors.multiplyByScalar(null, 0);
        List<Byte> result1 = Vectors.multiplyByScalar(emptyBytes, (byte) 0);
        List<Byte> result2 = Vectors.multiplyByScalar(bytes, (byte) 2);
        List<Short> result3 = Vectors.multiplyByScalar(shorts, (short) 10);
        List<Integer> result4 = Vectors.multiplyByScalar(integers, 3);
        List<Long> result5 = Vectors.multiplyByScalar(longs, 1L);
        List<Float> result6 = Vectors.multiplyByScalar(floats, 1.23f);
        List<Double> result7 = Vectors.multiplyByScalar(doubles, 0.0);

        // then
        Assert.assertTrue(result0.isEmpty());
        Assert.assertTrue(result1.isEmpty());
        Assertions.assertValues(result2, expectedBytes, Byte.class);
        Assertions.assertValues(result3, expectedShorts, Short.class);
        Assertions.assertValues(result4, expectedIntegers, Integer.class);
        Assertions.assertValues(result5, longs, Long.class);
        Assertions.assertFloats(result6, expectedFloats);
        Assertions.assertDoubles(result7, expectedDoubles);
    }

    @Test(expected = IllegalArgumentException.class)
    public void add1() {
        // when
        Vectors.add(Arrays.asList(0, 1), Arrays.asList(1, 0, 2));
    }

    @Test
    public void add2() {
        // given
        List<Byte> bytes1 = Arrays.asList((byte) 1, (byte) 0, (byte) -1);
        List<Byte> bytes2 = Arrays.asList((byte) -1, (byte) 1, (byte) 3);
        final List<Byte> expectedBytes = Arrays.asList((byte) 0, (byte) 1, (byte) 2);
        List<Short> shorts1 = Arrays.asList((short) -10_000, (short) 0, (short) 1010);
        List<Short> shorts2 = Arrays.asList((short) 20_000, (short) 0, (short) 101);
        final List<Short> expectedShorts = Arrays.asList((short) 10_000, (short) 0, (short) 1111);
        List<Integer> integers1 = Arrays.asList(101_010_011, 0, 1_111_000_100);
        List<Integer> integers2 = Arrays.asList(10_101_111, 0, 1);
        final List<Integer> expectedIntegers = Arrays.asList(111_111_122, 0, 1_111_000_101);
        List<Long> longs1 = Arrays.asList(111_001_100_000_110L, 0L, 111_111_111_111_111L);
        List<Long> longs2 = Arrays.asList(111L, 0L, -1L);
        final List<Long> expectedLongs = Arrays.asList(111_001_100_000_221L, 0L, 111_111_111_111_110L);
        List<Float> floats1 = Arrays.asList(-1.0f, 0.0f, 1.0f);
        List<Float> floats2 = Arrays.asList(2.0f, 2.0f, 2.0f);
        final List<Float> expectedFloats = Arrays.asList(1.0f, 2.0f, 3.0f);
        List<Double> doubles1 = Collections.singletonList(2.0 * Math.sqrt(7.0));
        List<Double> doubles2 = Collections.singletonList(2.0 * Math.sqrt(7.0));
        final List<Double> expectedDoubles = Collections.singletonList(4.0 * Math.sqrt(7.0));

        // when
        List<Number> result1 = Vectors.add(null, Collections.singletonList(0));
        List<Number> result2 = Vectors.add(Collections.singletonList(0), null);
        List<Number> result3 = Vectors.add(null, Collections.emptyList());
        List<Number> result4 = Vectors.add(Collections.emptyList(), null);
        List<Number> result5 = Vectors.add(Collections.emptyList(), Collections.singletonList(0));
        List<Number> result6 = Vectors.add(Collections.singletonList(0), Collections.emptyList());
        List<Byte> result7 = Vectors.add(bytes1, bytes2);
        List<Short> result8 = Vectors.add(shorts1, shorts2);
        List<Integer> result9 = Vectors.add(integers1, integers2);
        List<Long> result10 = Vectors.add(longs1, longs2);
        List<Float> result11 = Vectors.add(floats1, floats2);
        List<Double> result12 = Vectors.add(doubles1, doubles2);

        // then
        Assert.assertTrue(result1.isEmpty());
        Assert.assertTrue(result2.isEmpty());
        Assert.assertTrue(result3.isEmpty());
        Assert.assertTrue(result4.isEmpty());
        Assert.assertTrue(result5.isEmpty());
        Assert.assertTrue(result6.isEmpty());
        Assertions.assertValues(result7, expectedBytes, Byte.class);
        Assertions.assertValues(result8, expectedShorts, Short.class);
        Assertions.assertValues(result9, expectedIntegers, Integer.class);
        Assertions.assertValues(result10, expectedLongs, Long.class);
        Assertions.assertFloats(result11, expectedFloats);
        Assertions.assertDoubles(result12, expectedDoubles);
    }

    @Test
    public void cast2() {
        // given
        final Byte BYTE_VALUE = (byte) 123;
        final Short SHORT_VALUE = (short) 22_001;
        final Integer INTEGER_VALUE = 999_666;
        final Long LONG_VALUE = 1_222_333_444_555L;
        final Float FLOAT_VALUE = 1_073_741_824.75f;
        final Double DOUBLE_VALUE = 1_125_899_906_842_624.51;

        // when
        Byte byteToByte = Vectors.cast(BYTE_VALUE, Byte.class);
        Short byteToShort = Vectors.cast(BYTE_VALUE, Short.class);
        Integer byteToInteger = Vectors.cast(BYTE_VALUE, Integer.class);
        Long byteToLong = Vectors.cast(BYTE_VALUE, Long.class);
        Float byteToFloat = Vectors.cast(BYTE_VALUE, Float.class);
        Double byteToDouble = Vectors.cast(BYTE_VALUE, Double.class);

        Byte shortToByte = Vectors.cast(SHORT_VALUE, Byte.class);
        Short shortToShort = Vectors.cast(SHORT_VALUE, Short.class);
        Integer shortToInteger = Vectors.cast(SHORT_VALUE, Integer.class);
        Long shortToLong = Vectors.cast(SHORT_VALUE, Long.class);
        Float shortToFloat = Vectors.cast(SHORT_VALUE, Float.class);
        Double shortToDouble = Vectors.cast(SHORT_VALUE, Double.class);

        Byte integerToByte = Vectors.cast(INTEGER_VALUE, Byte.class);
        Short integerToShort = Vectors.cast(INTEGER_VALUE, Short.class);
        Integer integerToInteger = Vectors.cast(INTEGER_VALUE, Integer.class);
        Long integerToLong = Vectors.cast(INTEGER_VALUE, Long.class);
        Float integerToFloat = Vectors.cast(INTEGER_VALUE, Float.class);
        Double integerToDouble = Vectors.cast(INTEGER_VALUE, Double.class);

        Byte longToByte = Vectors.cast(LONG_VALUE, Byte.class);
        Short longToShort = Vectors.cast(LONG_VALUE, Short.class);
        Integer longToInteger = Vectors.cast(LONG_VALUE, Integer.class);
        Long longToLong = Vectors.cast(LONG_VALUE, Long.class);
        Float longToFloat = Vectors.cast(LONG_VALUE, Float.class);
        Double longToDouble = Vectors.cast(LONG_VALUE, Double.class);

        Byte floatToByte = Vectors.cast(FLOAT_VALUE, Byte.class);
        Short floatToShort = Vectors.cast(FLOAT_VALUE, Short.class);
        Integer floatToInteger = Vectors.cast(FLOAT_VALUE, Integer.class);
        Long floatToLong = Vectors.cast(FLOAT_VALUE, Long.class);
        Float floatToFloat = Vectors.cast(FLOAT_VALUE, Float.class);
        Double floatToDouble = Vectors.cast(FLOAT_VALUE, Double.class);

        Byte doubleToByte = Vectors.cast(DOUBLE_VALUE, Byte.class);
        Short doubleToShort = Vectors.cast(DOUBLE_VALUE, Short.class);
        Integer doubleToInteger = Vectors.cast(DOUBLE_VALUE, Integer.class);
        Long doubleToLong = Vectors.cast(DOUBLE_VALUE, Long.class);
        Float doubleToFloat = Vectors.cast(DOUBLE_VALUE, Float.class);
        Double doubleToDouble = Vectors.cast(DOUBLE_VALUE, Double.class);

        // then
        Assert.assertNotNull(byteToByte);
        Assert.assertTrue(byteToByte == (byte) 123);
        Assertions.assertType(byteToByte, Byte.class);
        Assert.assertNotNull(byteToShort);
        Assert.assertTrue(byteToShort == (short) 123);
        Assertions.assertType(byteToShort, Short.class);
        Assert.assertNotNull(byteToInteger);
        Assert.assertTrue(byteToInteger == (short) 123);
        Assertions.assertType(byteToInteger, Integer.class);
        Assert.assertNotNull(byteToLong);
        Assert.assertTrue(byteToLong == 123L);
        Assertions.assertType(byteToLong, Long.class);
        Assert.assertNotNull(byteToFloat);
        assertThat(byteToFloat.doubleValue(), closeTo(123.0f, Assertions.IBM_FLOAT_SURROUNDING));
        Assertions.assertType(byteToFloat, Float.class);
        Assert.assertNotNull(byteToDouble);
        assertThat(byteToDouble, closeTo(123.0, Assertions.IBM_FLOAT_SURROUNDING));
        Assertions.assertType(byteToDouble, Double.class);

        Assert.assertNull(shortToByte);
        Assert.assertNotNull(shortToShort);
        Assert.assertTrue(shortToShort == (short) 22_001);
        Assertions.assertType(shortToShort, Short.class);
        Assert.assertNotNull(shortToInteger);
        Assert.assertTrue(shortToInteger == 22_001);
        Assertions.assertType(shortToInteger, Integer.class);
        Assert.assertNotNull(shortToLong);
        Assert.assertTrue(shortToLong == 22_001L);
        Assertions.assertType(shortToLong, Long.class);
        Assert.assertNotNull(shortToFloat);
        assertThat(shortToFloat.doubleValue(), closeTo(22_001.0f, Assertions.IBM_FLOAT_SURROUNDING));
        Assertions.assertType(shortToFloat, Float.class);
        Assert.assertNotNull(shortToDouble);
        assertThat(shortToDouble, closeTo(22_001.0, Assertions.IBM_FLOAT_SURROUNDING));
        Assertions.assertType(shortToDouble, Double.class);

        Assert.assertNull(integerToByte);
        Assert.assertNull(integerToShort);
        Assert.assertNotNull(integerToInteger);
        Assert.assertTrue(integerToInteger == 999_666);
        Assertions.assertType(integerToInteger, Integer.class);
        Assert.assertNotNull(integerToLong);
        Assert.assertTrue(integerToLong == 999_666L);
        Assertions.assertType(integerToLong, Long.class);
        Assert.assertNotNull(integerToFloat);
        assertThat(integerToFloat.doubleValue(), closeTo(999_666.0f, Assertions.IBM_FLOAT_SURROUNDING));
        Assertions.assertType(integerToFloat, Float.class);
        Assert.assertNotNull(integerToDouble);
        assertThat(integerToDouble, closeTo(999_666.0, Assertions.IBM_FLOAT_SURROUNDING));
        Assertions.assertType(integerToDouble, Double.class);

        Assert.assertNull(longToByte);
        Assert.assertNull(longToShort);
        Assert.assertNull(longToInteger);
        Assert.assertNotNull(longToLong);
        Assert.assertTrue(longToLong == 1_222_333_444_555L);
        Assertions.assertType(longToLong, Long.class);
        Assert.assertNotNull(longToFloat);
        assertThat(longToFloat.doubleValue(), closeTo(1_222_333_444_555.0f, Assertions.IBM_FLOAT_SURROUNDING));
        Assertions.assertType(longToFloat, Float.class);
        Assert.assertNotNull(longToDouble);
        assertThat(longToDouble, closeTo(1_222_333_444_555.0, Assertions.IBM_FLOAT_SURROUNDING));
        Assertions.assertType(longToDouble, Double.class);

        Assert.assertNull(floatToByte);
        Assert.assertNull(floatToShort);
        Assert.assertNull(floatToInteger);
        Assert.assertNull(floatToLong);
        Assert.assertNotNull(floatToFloat);
        assertThat(floatToFloat.doubleValue(), closeTo(1_073_741_824.75f, Assertions.IBM_FLOAT_SURROUNDING));
        Assertions.assertType(floatToFloat, Float.class);
        Assert.assertNotNull(floatToDouble);
        assertThat(floatToDouble, closeTo(1_073_741_824.75f, 4.75));
        Assertions.assertType(floatToDouble, Double.class);

        Assert.assertNull(doubleToByte);
        Assert.assertNull(doubleToShort);
        Assert.assertNull(doubleToInteger);
        Assert.assertNull(doubleToLong);
        Assert.assertNotNull(doubleToFloat);
        Assert.assertNotNull(doubleToDouble);
        assertThat(doubleToDouble, closeTo(1_125_899_906_842_624.51, 1.13E15));
        Assertions.assertType(doubleToDouble, Double.class);
    }

    @Test
    public void multiplyAsMatrix1() {
        // given
        final List<Byte> bytes1 = Arrays.asList((byte) -2, (byte) 0, (byte) 1);
        final List<Byte> bytes2 = Arrays.asList((byte) 3, (byte) 0, (byte) 1);
        final Byte expectedByte = (byte) -5;

        final List<Short> shorts1 = Arrays.asList((short) -100, (short) 0, (short) 10_000);
        final List<Short> shorts2 = Arrays.asList((short) 30, (short) 0, (short) 10);
        final Short expectedShort = (short) 97_000;

        final List<Integer> integers1 = Arrays.asList(-1_000_000, 0, 10_000_001);
        final List<Integer> integers2 = Arrays.asList(-1, 0, 99);
        final Integer expectedInteger = 991_000_099;

        final List<Long> longs1 = Arrays.asList(1_111_111_111L, 0L, 123_321L);
        final List<Long> longs2 = Arrays.asList(-9L, 0L, 321_123L);
        final Long expectedLong = 29_601_209_484L;

        final List<Float> floats1 = Arrays.asList(-1.23f, 0.0f, 23.2323f);
        final List<Float> floats2 = Arrays.asList(1.23f, 0.0f, -23.2323f);
        final Float expectedFloat = -541.25266329f;

        final List<Double> doubles1 = Arrays.asList(Math.sqrt(5.0), 0.0, 11.0 / 3.0);
        final List<Double> doubles2 = Arrays.asList(2.0, -0.0, 3.0 / 11.0);
        final Double expectedDouble = (2.0 * Math.sqrt(5.0)) + 1.0;

        // when
        Byte byteValue = Vectors.multiplyAsMatrix(bytes1, bytes2);
        Short shortValue = Vectors.multiplyAsMatrix(shorts1, shorts2);
        Integer integerValue = Vectors.multiplyAsMatrix(integers1, integers2);
        Long longValue = Vectors.multiplyAsMatrix(longs1, longs2);
        Float floatValue = Vectors.multiplyAsMatrix(floats1, floats2);
        Double doubleValue = Vectors.multiplyAsMatrix(doubles1, doubles2);
        Number value1 = Vectors.multiplyAsMatrix(null, Collections.singletonList(0));
        Number value2 = Vectors.multiplyAsMatrix(Collections.singletonList(0), null);
        Number value3 = Vectors.multiplyAsMatrix(Collections.emptyList(), Collections.singletonList(0));
        Number value4 = Vectors.multiplyAsMatrix(Collections.singletonList(0), Collections.emptyList());
        Number value5 = Vectors.multiplyAsMatrix(null, Collections.emptyList());
        Number value6 = Vectors.multiplyAsMatrix(Collections.emptyList(), null);

        // then
        Assert.assertNotNull(byteValue);
        Assertions.assertType(byteValue, Byte.class);
        Assert.assertTrue(byteValue.equals(expectedByte));

        Assert.assertNotNull(shortValue);
        Assertions.assertType(shortValue, Short.class);
        Assert.assertTrue(shortValue.equals(expectedShort));

        Assert.assertNotNull(integerValue);
        Assertions.assertType(integerValue, Integer.class);
        Assert.assertTrue(integerValue.equals(expectedInteger));

        Assert.assertNotNull(longValue);
        Assertions.assertType(longValue, Long.class);
        Assert.assertTrue(longValue.equals(expectedLong));

        Assert.assertNotNull(floatValue);
        Assertions.assertType(floatValue, Float.class);
        assertThat(floatValue.doubleValue(), closeTo(expectedFloat, 0.1E-3));

        Assert.assertNotNull(doubleValue);
        Assertions.assertType(doubleValue, Double.class);
        assertThat(doubleValue, closeTo(expectedDouble, Assertions.IBM_FLOAT_SURROUNDING));

        Assert.assertNull(value1);
        Assert.assertNull(value2);
        Assert.assertNull(value3);
        Assert.assertNull(value4);
        Assert.assertNull(value5);
        Assert.assertNull(value6);
    }

    @Test(expected = IllegalArgumentException.class)
    public void multiplyAsMatrix2() {
        // when
        Number value1 = Vectors.multiplyAsMatrix(Collections.singletonList(1.0), Arrays.asList(0.0, -1.0));
    }

    @Test
    public void isAs() {
        // given
        final List<Byte> bytes1 = Arrays.asList((byte) -1, (byte) 0, (byte) 1);
        final List<Short> shorts1 = Arrays.asList((short) -10_000, (short) 0, (short) 10_000);
        final List<Integer> integers1 = Arrays.asList(-100_000, 0, 100_000);
        final List<Long> longs1 = Arrays.asList(-10_000_000_000L, 0L, 10_000_000_000L);
        final List<Float> floats1 = Arrays.asList(-1.1f, 0.0f, 1.1f);
        final List<Double> doubles1 = Arrays.asList(-1.1, 0.0, 1.1);
        List<Byte> bytes2 = new ArrayList<>(Arrays.asList((byte) -1, (byte) 0, (byte) 1));
        List<Short> shorts2 = new ArrayList<>(Arrays.asList((short) -10_000, (short) 0, (short) 10_000));
        List<Integer> integers2 = new ArrayList<>(Arrays.asList(-100_000, 0, 100_000));
        List<Long> longs2 = new ArrayList<>(Arrays.asList(-10_000_000_000L, 0L, 10_000_000_000L));
        List<Float> floats2 = new ArrayList<>(Arrays.asList(-1.1f, 0.0f, 1.1f));
        List<Double> doubles2 = new ArrayList<>(Arrays.asList(-1.1, 0.0, 1.1));

        // when
        boolean result1 = Vectors.isAs(null, Collections.emptyList());
        boolean result2 = Vectors.isAs(Collections.emptyList(), null);
        boolean result3 = Vectors.isAs(null, null);
        boolean result4 = Vectors.isAs(Collections.emptyList(), Collections.singletonList(0));

        boolean result5 = Vectors.isAs(bytes1, bytes2);
        boolean result6 = Vectors.isAs(shorts1, shorts2);
        boolean result7 = Vectors.isAs(integers1, integers2);
        boolean result8 = Vectors.isAs(longs1, longs2);
        boolean result9 = Vectors.isAs(floats1, floats2);
        boolean result10 = Vectors.isAs(doubles1, doubles2);

        boolean result11 = Vectors.isAs(bytes1, Collections.singletonList((byte) 0));
        boolean result12 = Vectors.isAs(shorts1, Collections.singletonList((short) 0));
        boolean result13 = Vectors.isAs(integers1, Collections.singletonList(0));
        boolean result14 = Vectors.isAs(longs1, Collections.singletonList(0L));
        boolean result15 = Vectors.isAs(floats1, Collections.singletonList(0.0f));
        boolean result16 = Vectors.isAs(doubles1, Collections.singletonList(0.0));

        boolean result17 = Vectors.isAs(doubles1, Arrays.asList(-1.1, 0.000000000001, 1.1));
        boolean result18 = Vectors.isAs(doubles1, Arrays.asList(-1.100000000000001, 0.0, 1.1));

        // then
        Assert.assertFalse(result1);
        Assert.assertFalse(result2);
        Assert.assertTrue(result3);
        Assert.assertFalse(result4);

        Assert.assertTrue(result5);
        Assert.assertTrue(result6);
        Assert.assertTrue(result7);
        Assert.assertTrue(result8);
        Assert.assertTrue(result9);
        Assert.assertTrue(result10);

        Assert.assertFalse(result11);
        Assert.assertFalse(result12);
        Assert.assertFalse(result13);
        Assert.assertFalse(result14);
        Assert.assertFalse(result15);
        Assert.assertFalse(result16);

        Assert.assertFalse(result17);
        Assert.assertFalse(result18);
    }

}

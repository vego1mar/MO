package pl.mo.general;

import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

import org.junit.Assert;
import org.junit.Test;

public class PrimitivesHelperTest {

    private static final double IBM_FLOAT_SURROUNDING = 5.96E-8;

    @Test
    public void getDouble() {
        // when
        Double value1 = PrimitivesHelper.getDouble("-12.34");
        String string1 = PrimitivesHelper.getErrorMessage();
        Double value2 = PrimitivesHelper.getDouble("0.00000");
        String string2 = PrimitivesHelper.getErrorMessage();
        Double value3 = PrimitivesHelper.getDouble("666");
        String string3 = PrimitivesHelper.getErrorMessage();

        // then
        Assert.assertThat(value1, closeTo(-12.34, IBM_FLOAT_SURROUNDING));
        Assert.assertNull(string1);
        Assert.assertThat(value2, closeTo(0.00000, IBM_FLOAT_SURROUNDING));
        Assert.assertNull(string2);
        Assert.assertThat(value3, closeTo(666, IBM_FLOAT_SURROUNDING));
        Assert.assertNull(string3);
    }

    @Test
    public void getErrorMessage() {
        // when
        Double value1 = PrimitivesHelper.getDouble("0,23");
        String string1 = PrimitivesHelper.getErrorMessage();
        PrimitivesHelper.getDouble("0.0");
        Double value2 = PrimitivesHelper.getDouble("0xABC");
        String string2 = PrimitivesHelper.getErrorMessage();
        Integer value3 = PrimitivesHelper.getInteger("0.0");
        String string3 = PrimitivesHelper.getErrorMessage();
        Integer value4 = PrimitivesHelper.getInteger(null);
        String string4 = PrimitivesHelper.getErrorMessage();

        // then
        Assert.assertNull(value1);
        Assert.assertNotNull(string1);
        Assert.assertNull(value2);
        Assert.assertNotNull(string2);
        Assert.assertNull(value3);
        Assert.assertNotNull(string3);
        Assert.assertNull(value4);
        Assert.assertNotNull(string4);
    }

    @Test
    public void getInteger() {
        // when
        Integer value1 = PrimitivesHelper.getInteger("-23");
        String string1 = PrimitivesHelper.getErrorMessage();
        Integer value2 = PrimitivesHelper.getInteger("0");
        String string2 = PrimitivesHelper.getErrorMessage();
        Integer value3 = PrimitivesHelper.getInteger(String.valueOf(Integer.MAX_VALUE));
        String string3 = PrimitivesHelper.getErrorMessage();

        // then
        Assert.assertThat(value1, is(-23));
        Assert.assertNull(string1);
        Assert.assertThat(value2, is(0));
        Assert.assertNull(string2);
        Assert.assertThat(value3, instanceOf(Integer.class));
        Assert.assertNull(string3);
    }

    @Test
    public void castInto() {
        // when
        Object cast1 = PrimitivesHelper.castInto(new Object(), Object.class);
        Object cast2 = PrimitivesHelper.castInto(0, Integer.class);
        Object cast3 = PrimitivesHelper.castInto(0L, Long.class);
        Object cast4 = PrimitivesHelper.castInto(0, Double.class);
        Object cast5 = PrimitivesHelper.castInto("0", String.class);

        // then
        Assert.assertNotNull(cast1);
        Assert.assertNotNull(cast2);
        Assert.assertNotNull(cast3);
        Assert.assertNull(cast4);
        Assert.assertNotNull(cast5);
    }

}

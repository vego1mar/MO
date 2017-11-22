package pl.mo.general;

import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

import org.junit.Assert;
import org.junit.Test;

public class PrimitivesParserTest {

    private static final double IBM_FLOAT_SURROUNDING = 5.96E-8;

    @Test
    public void getDoubleTest() {
        // when
        Double value1 = PrimitivesParser.getDouble("-12.34");
        String string1 = PrimitivesParser.getErrorMessage();
        Double value2 = PrimitivesParser.getDouble("0.00000");
        String string2 = PrimitivesParser.getErrorMessage();
        Double value3 = PrimitivesParser.getDouble("666");
        String string3 = PrimitivesParser.getErrorMessage();

        // then
        Assert.assertThat(value1, closeTo(-12.34, IBM_FLOAT_SURROUNDING));
        Assert.assertNull(string1);
        Assert.assertThat(value2, closeTo(0.00000, IBM_FLOAT_SURROUNDING));
        Assert.assertNull(string2);
        Assert.assertThat(value3, closeTo(666, IBM_FLOAT_SURROUNDING));
        Assert.assertNull(string3);
    }

    @Test
    public void getErrorMessageTest() {
        // when
        Double value1 = PrimitivesParser.getDouble("0,23");
        String string1 = PrimitivesParser.getErrorMessage();
        PrimitivesParser.getDouble("0.0");
        Double value2 = PrimitivesParser.getDouble("0xABC");
        String string2 = PrimitivesParser.getErrorMessage();
        Integer value3 = PrimitivesParser.getInteger("0.0");
        String string3 = PrimitivesParser.getErrorMessage();
        Integer value4 = PrimitivesParser.getInteger(null);
        String string4 = PrimitivesParser.getErrorMessage();

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
    public void getIntegerTest() {
        // when
        Integer value1 = PrimitivesParser.getInteger("-23");
        String string1 = PrimitivesParser.getErrorMessage();
        Integer value2 = PrimitivesParser.getInteger("0");
        String string2 = PrimitivesParser.getErrorMessage();
        Integer value3 = PrimitivesParser.getInteger(String.valueOf(Integer.MAX_VALUE));
        String string3 = PrimitivesParser.getErrorMessage();

        // then
        Assert.assertThat(value1, is(-23));
        Assert.assertNull(string1);
        Assert.assertThat(value2, is(0));
        Assert.assertNull(string2);
        Assert.assertThat(value3, instanceOf(Integer.class));
        Assert.assertNull(string3);
    }

}

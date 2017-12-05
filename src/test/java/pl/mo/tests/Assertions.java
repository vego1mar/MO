package pl.mo.tests;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;

import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.junit.Assert;

public strictfp class Assertions {

    public static final double IBM_FLOAT_SURROUNDING = 5.96E-8;

    private Assertions() {
        // This should be a utility class.
    }

    public static void assertFloats(@NotNull List<Float> actual, List<Float> expected) {
        for (int i = 0; i < actual.size(); i++) {
            assertThat(actual.get(i).doubleValue(), closeTo(expected.get(i).doubleValue(), IBM_FLOAT_SURROUNDING));
        }
    }

    public static void assertDoubles(@NotNull List<Double> actual, List<Double> expected) {
        for (int i = 0; i < actual.size(); i++) {
            assertThat(actual.get(i), closeTo(expected.get(i), IBM_FLOAT_SURROUNDING));
        }
    }

    public static <T, E> void assertTypes(@NotNull List<T> actual, List<E> expected) {
        for (int i = 0; i < actual.size(); i++) {
            Assert.assertTrue(actual.get(i).getClass() == expected.get(i).getClass());
        }
    }

    public static <T extends Number, E> void assertValues(@NotNull List<T> actual, List<T> expected, Class<T> initialType) {
        for (int i = 0; i < actual.size(); i++) {
            if (initialType == Byte.class) {
                Assert.assertTrue(actual.get(i).byteValue() == expected.get(i).byteValue());
            } else if (initialType == Short.class) {
                Assert.assertTrue(actual.get(i).shortValue() == expected.get(i).shortValue());
            } else if (initialType == Integer.class) {
                Assert.assertTrue(actual.get(i).intValue() == expected.get(i).intValue());
            } else if (initialType == Long.class) {
                Assert.assertTrue(actual.get(i).longValue() == expected.get(i).longValue());
            } else if (initialType == Float.class || initialType == Double.class) {
                assertThat(actual.get(i).doubleValue(), closeTo(expected.get(i).doubleValue(), IBM_FLOAT_SURROUNDING));
            }
        }
    }

}

package pl.mo.general;

import java.util.List;
import java.util.Random;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public final strictfp class Numbers {

    private static final Random generator = new Random(System.nanoTime());

    private Numbers() {
        // This should be a utility class.
    }

    public static double getRandomGaussian(@NotNull Number minimum, @NotNull Number maximum) {
        if (minimum.doubleValue() > maximum.doubleValue()) {
            List<Number> values = swap(minimum, maximum);
            minimum = values.get(0).doubleValue();
            maximum = values.get(1).doubleValue();
        }

        double gaussian = generator.nextGaussian();
        double mean = (maximum.doubleValue() + minimum.doubleValue()) / 2.0;
        double stdDeviation = (1.0 / 6.0) * Math.abs(maximum.doubleValue() - mean);
        return (gaussian * stdDeviation) + mean;
    }

    @NotNull
    @Contract(pure = true)
    public static List<Number> swap(Number a, Number b) {
        return List.of(b, a);
    }

    public static boolean isInRange(Number value, @NotNull Number left, @NotNull Number right) {
        if (left.doubleValue() > right.doubleValue()) {
            List<Number> swaps = swap(left, right);
            left = swaps.get(0);
            right = swaps.get(1);
        }

        return value.doubleValue() > left.doubleValue() && value.doubleValue() < right.doubleValue();
    }

}

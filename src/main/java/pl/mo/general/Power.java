package pl.mo.general;

import org.jetbrains.annotations.Contract;

/**
 * Gathers different methods of performing the mathematical exponentiation operation.
 *
 * @version 1.0
 */
public final strictfp class Power {

    private Power() {
        // This should be a utility class.
    }

    /**
     * Recursive version of the fast power algorithm.
     *
     * @param x is the base of the power
     * @param n is the exponent of the power, that should be <b>n > 0</b>
     * @return <b>x</b> raised to the power of <b>n</b>, that is <b>x<sup>n</sup></b>
     * @throws StackOverflowError if <b>n < 0</b>
     * @since 1.0
     */
    @Contract(pure = true)
    public static double getFast(double x, int n) {
        if (n == 0) {
            return 1.0;
        }

        if (n % 2 != 0) {
            double residual = getFast(x, (n - 1) / 2);
            return x * (residual * residual);
        }

        double residual = getFast(x, n / 2);
        return residual * residual;
    }

}

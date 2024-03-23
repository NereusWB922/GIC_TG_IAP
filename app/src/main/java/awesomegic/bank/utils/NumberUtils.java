package awesomegic.bank.utils;

import java.math.BigDecimal;

/**
 * Utility class for numbers.
 */
public class NumberUtils {
    /**
     * Checks if the given number is non-negative.
     */
    public static void requireNonNegative(BigDecimal number, String msg) {
        if (number.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException(msg);
        }
    }

    /**
     * Checks if the given number is non-zero.
     */
    public static void requireNonZero(BigDecimal number, String msg) {
        if (number.compareTo(BigDecimal.ZERO) == 0) {
            throw new IllegalArgumentException(msg);
        }
    }

    /**
     * Checks if the given number is positive.
     */
    public static void requirePositive(BigDecimal number, String msg) {
        if (number.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(msg);
        }
    }
}

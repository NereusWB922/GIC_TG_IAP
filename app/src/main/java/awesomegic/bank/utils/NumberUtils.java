package awesomegic.bank.utils;

import java.math.BigDecimal;

/**
 * Utility class for numbers.
 */
public class NumberUtils {
    /**
     * Checks if the given number is non-negative.
     */
    public static void checkNonNegative(BigDecimal number, String msg) {
        if (number.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException(msg);
        }
    }

    /**
     * Checks if the given number is positive.
     */
    public static void checkPositive(BigDecimal number, String msg) {
        if (number.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(msg);
        }
    }
}

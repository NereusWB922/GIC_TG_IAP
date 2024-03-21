package awesomegic.bank.utils;

/**
 * Utility class for numbers.
 */
public class NumberUtils {
    /**
     * Checks if the given number is non-negative.
     */
    public static void checkNonNegative(double number, String msg) {
        if (number < 0) {
            throw new IllegalArgumentException(msg);
        }
    }

    /**
     * Checks if the given number is positive.
     */
    public static void checkPositive(double number, String msg) {
        if (number <= 0) {
            throw new IllegalArgumentException(msg);
        }
    }
}

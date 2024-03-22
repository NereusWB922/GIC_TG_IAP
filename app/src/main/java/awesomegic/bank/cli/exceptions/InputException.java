package awesomegic.bank.cli.exceptions;

/**
 * Exception thrown when an invalid input is provided in the application.
 */
public class InputException extends Exception {
    /**
     * Constructs an {@code InputException} with the specified detail message.
     *
     * @param msg the detail message describing the error.
     */
    public InputException(String msg) {
        super(msg);
    }
}

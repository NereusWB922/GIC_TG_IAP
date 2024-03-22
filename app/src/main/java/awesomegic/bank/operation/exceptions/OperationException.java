package awesomegic.bank.operation.exceptions;

/**
 * Exception thrown when an error occurred during the execution of an operation.
 */
public class OperationException extends Exception {
    /**
     * Constructs an {@code OperationException} with the specified detail message.
     *
     * @param msg the detail message describing the error.
     */
    public OperationException(String msg) {
        super(msg);
    }
}

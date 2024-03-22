package awesomegic.bank.operation;

import awesomegic.bank.cli.exceptions.InputException;
import awesomegic.bank.operation.exceptions.OperationException;

/**
 * Represents a generic operation in the bank system
 */
public interface Operation {

    /**
     * Executes the operation and returns the result.
     * 
     * @return An {@link OperationResult} representing the outcome of the operation.
     * @throws InputException If there is an issue with the input required for the operation.
     * @throws OperationException If the operation encounters an error during its execution.
     */
    public OperationResult execute() throws InputException, OperationException;
}

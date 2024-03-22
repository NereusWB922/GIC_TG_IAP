package awesomegic.bank.operation;

/**
 * Represents an operation to quit the bank system.
 */
public class QuitOperation implements Operation {
    public static final String OPERATION_KEY = "q";

    QuitOperation(){
    }
    
     /**
     * This method returns a special {@link OperationResult} indicating the application
     * should terminate.
     *
     * @return An {@link OperationResult} with an empty message and the exit flag set to true.
     */
    @Override
    public OperationResult execute() {
        return new OperationResult("", true);
    }
}

package awesomegic.bank.operation;

import awesomegic.bank.model.account.BankAccount;

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
    public OperationResult execute(BankAccount account) {
        return new OperationResult(account, true);
    }
}

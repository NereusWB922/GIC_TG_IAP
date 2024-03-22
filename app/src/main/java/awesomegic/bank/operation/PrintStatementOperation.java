package awesomegic.bank.operation;

import awesomegic.bank.model.account.AccountStatement;
import awesomegic.bank.model.account.BankAccount;

/**
 * Represents an operation to print the account statement.
 */
public class PrintStatementOperation implements Operation {
    public static final String OPERATION_KEY = "p";
    private final BankAccount account;

    /**
     * Constructs a new PrintStatementOperation with the specified bank account.
     *
     * @param account The {@link BankAccount} for which the statement will be printed.
     */
    PrintStatementOperation(BankAccount account) {
        this.account = account;
    }

     /**
     * Retrieves the account statement from the associated {@link BankAccount} and
     * returns it as a string representation.
     *
     * @return An {@link OperationResult} containing the account statement.
     */
    @Override
    public OperationResult execute() {
        AccountStatement statement = this.account.generateStatement();
        return new OperationResult(statement.toString());
    }
}

package awesomegic.bank.operation;

import static java.util.Objects.requireNonNull;

import awesomegic.bank.model.account.AccountStatement;
import awesomegic.bank.model.account.BankAccount;

/**
 * Represents an operation to print the account statement.
 */
public class PrintStatementOperation implements Operation {
    public static final String OPERATION_KEY = "p";

    PrintStatementOperation() {
    }

     /**
     * Retrieves the account statement from the associated {@link BankAccount} and
     * returns it as a string representation.
     *
     * @param account The {@link BankAccount} for which the statement will be printed.
     * @return An {@link OperationResult} containing the account statement.
     */
    @Override
    public OperationResult execute(BankAccount account) {
        requireNonNull(account);

        AccountStatement statement = account.generateStatement();

        return new OperationResult(account, statement.toString());
    }
}

package awesomegic.bank.operation;

import java.math.BigDecimal;

import awesomegic.bank.cli.Cli;
import awesomegic.bank.cli.exceptions.InputException;
import awesomegic.bank.model.account.BankAccount;

/**
 * Represents a deposit operation for a bank account.
 */
public class DepositOperation implements Operation {
    public static final String OPERATION_KEY = "d";
    private static final String TRANSACTION_TYPE = "deposit";
    private static final String MESSAGE_DEPOSIT_SUCCESS = "Deposit successful. $%.2f has been added to your account.";
    private final BankAccount account;
    private final Cli cli;

    /**
     * Constructs a new DepositOperation with a specified bank account and CLI interface.
     * 
     * @param account The {@link BankAccount} to which the deposit will be made.
     * @param cli The {@link Cli} instance used to interact with the user.
     */
    DepositOperation(BankAccount account, Cli cli) {
        this.account = account;
        this.cli = cli;
    }

    /**
     * Prompts the user for the deposit amount, validates the input,
     * and updates the bank account balance.
     * 
     * @return An {@link OperationResult} indicating the outcome of the deposit operation.
     * @throws InputException If the input read from the CLI is invalid.
     */
    @Override
    public OperationResult execute() throws InputException {
        BigDecimal amount = this.cli.readTransactionAmount(TRANSACTION_TYPE);

        this.account.deposit(amount);

        return new OperationResult(String.format(MESSAGE_DEPOSIT_SUCCESS, amount));
    }
}

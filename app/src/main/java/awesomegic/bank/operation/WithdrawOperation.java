package awesomegic.bank.operation;

import awesomegic.bank.cli.Cli;
import awesomegic.bank.cli.exceptions.InputException;
import awesomegic.bank.model.account.BankAccount;
import awesomegic.bank.operation.exceptions.OperationException;

/**
 * Represents an operation to withdraw funds from a bank account.
 */
public class WithdrawOperation implements Operation {
    public static final String OPERATION_KEY = "w";
    private static final String TRANSACTION_TYPE = "withdraw";
    private static final String MESSAGE_INSUFFICIENT_BALANCE = "Insufficient balance. Unable to complete withdrawal.";
    private static final String MESSAGE_WITHDRAWAL_SUCCESS = "Thank you. $%.2f has been withdrawn.";
    private final BankAccount account;
    private final Cli cli;

    /**
     * Constructs a new WithdrawOperation with the specified bank account and CLI interface.
     *
     * @param account The {@link BankAccount} from which the withdrawal will be made.
     * @param cli     The {@link Cli} instance used to interact with the user.
     */
    WithdrawOperation(BankAccount account, Cli cli) {
        this.account = account;
        this.cli = cli;
    }

    /**
     * Prompts the user for the withdrawal amount, validates the input,
     * and updates the bank account balance.
     * 
     * @return An {@link OperationResult} indicating the outcome of the withdrawal operation.
     * @throws InputException     If the input read from the CLI is invalid.
     * @throws OperationException If the account balance is insufficient for the withdrawal.
     */
    @Override
    public OperationResult execute() throws InputException, OperationException {
        double amount = this.cli.readTransactionAmount(TRANSACTION_TYPE);

        if (!this.account.isBalanceSufficient(amount)) {
            throw new OperationException(MESSAGE_INSUFFICIENT_BALANCE);
        }

        this.account.withdraw(amount);

        return new OperationResult(String.format(MESSAGE_WITHDRAWAL_SUCCESS, amount));
    }
}

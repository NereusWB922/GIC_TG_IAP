package awesomegic.bank.operation;

import static awesomegic.bank.cli.Message.MESSAGE_NON_POSITIVE_DEPOSIT_AMOUNT;
import static awesomegic.bank.utils.NumberUtils.requirePositive;
import static java.util.Objects.requireNonNull;

import java.math.BigDecimal;

import awesomegic.bank.model.account.BankAccount;

/**
 * Represents a deposit operation for a bank account.
 */
public class DepositOperation implements Operation {
    public static final String OPERATION_KEY = "d";
    public static final String TRANSACTION_TYPE = "deposit";
    private static final String MESSAGE_DEPOSIT_SUCCESS = "Deposit successful. $%.2f has been added to your account.";
    private final BigDecimal amount;

    /**
     * Creates a {@code DepositOperation} for a specified {@link BankAccount} and deposit amount.
     * 
     * @param amount  The amount to be deposited.
     */
    DepositOperation(BigDecimal amount) {
        requireNonNull(amount);
        requirePositive(amount, MESSAGE_NON_POSITIVE_DEPOSIT_AMOUNT);

        this.amount = amount;
    }

    /**
     * Executes the deposit operation on the associated {@link BankAccount}.
     * 
     * @param account The {@link BankAccount} to which the deposit amount will be added.
     * @return An {@link OperationResult} indicating the success of the deposit operation.
     */
    @Override
    public OperationResult execute(BankAccount account) {
        requireNonNull(account);

        BankAccount updatedAccount = account.deposit(this.amount);

        return new OperationResult(updatedAccount, String.format(MESSAGE_DEPOSIT_SUCCESS, amount));
    }
}

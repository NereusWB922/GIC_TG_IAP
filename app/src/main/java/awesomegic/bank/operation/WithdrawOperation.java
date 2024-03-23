package awesomegic.bank.operation;

import static awesomegic.bank.cli.Message.MESSAGE_NON_POSITIVE_WITHDRAWAL_AMOUNT;
import static awesomegic.bank.utils.NumberUtils.requirePositive;
import static java.util.Objects.requireNonNull;

import java.math.BigDecimal;

import awesomegic.bank.model.account.BankAccount;
import awesomegic.bank.operation.exceptions.OperationException;

/**
 * Represents an operation to withdraw funds from a bank account.
 */
public class WithdrawOperation implements Operation {
    public static final String OPERATION_KEY = "w";
    public static final String TRANSACTION_TYPE = "withdraw";
    private static final String MESSAGE_INSUFFICIENT_BALANCE = "Insufficient balance. Unable to complete withdrawal.";
    private static final String MESSAGE_WITHDRAWAL_SUCCESS = "Thank you. $%.2f has been withdrawn.";
    private final BigDecimal amount;

    /**
     * Constructs a new {@code WithdrawOperation} with the specified withdrawal amount.
     * 
     * @param amount The amount to be withdrawed.
     */
    WithdrawOperation(BigDecimal amount) {
        requireNonNull(amount);
        requirePositive(amount, MESSAGE_NON_POSITIVE_WITHDRAWAL_AMOUNT);

        this.amount = amount;
    }

    /**
     * Executes the withdraw operation on the associated {@link BankAccount}.
     * 
     * @param account The {@link BankAccount} from which the withdrawal will be made.
     * @return An {@link OperationResult} indicating the outcome of the withdrawal operation.
     * @throws OperationException If the account balance is insufficient for the withdrawal.
     */
    @Override
    public OperationResult execute(BankAccount account) throws OperationException {
        requireNonNull(account);

        if (!account.isBalanceSufficient(this.amount)) {
            throw new OperationException(MESSAGE_INSUFFICIENT_BALANCE);
        }

        BankAccount updatedAccount = account.withdraw(this.amount);

        return new OperationResult(updatedAccount, String.format(MESSAGE_WITHDRAWAL_SUCCESS, this.amount));
    }
}

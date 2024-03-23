package awesomegic.bank.operation;

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
    private final BankAccount account;
    private final BigDecimal amount;

    /**
     * Constructs a new {@code WithdrawOperation} with the specified {@link BankAccount} and withdrawal amount.
     *
     * @param account The {@link BankAccount} from which the withdrawal will be made.
     * @param amount The amount to be withdrawed.
     */
    WithdrawOperation(BankAccount account, BigDecimal amount) {
        this.account = account;
        this.amount = amount;
    }

    /**
     * Executes the withdraw operation on the associated {@link BankAccount}.
     * 
     * @return An {@link OperationResult} indicating the outcome of the withdrawal operation.
     * @throws OperationException If the account balance is insufficient for the withdrawal.
     */
    @Override
    public OperationResult execute() throws OperationException {
        if (!this.account.isBalanceSufficient(this.amount)) {
            throw new OperationException(MESSAGE_INSUFFICIENT_BALANCE);
        }

        this.account.withdraw(this.amount);

        return new OperationResult(String.format(MESSAGE_WITHDRAWAL_SUCCESS, this.amount));
    }
}

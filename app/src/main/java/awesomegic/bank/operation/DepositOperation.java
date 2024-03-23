package awesomegic.bank.operation;

import java.math.BigDecimal;

import awesomegic.bank.model.account.BankAccount;

/**
 * Represents a deposit operation for a bank account.
 */
public class DepositOperation implements Operation {
    public static final String OPERATION_KEY = "d";
    public static final String TRANSACTION_TYPE = "deposit";
    private static final String MESSAGE_DEPOSIT_SUCCESS = "Deposit successful. $%.2f has been added to your account.";
    private final BankAccount account;
    private final BigDecimal amount;

    /**
     * Creates a {@code DepositOperation} for a specified {@link BankAccount} and deposit amount.
     * 
     * @param account The {@link BankAccount} to which the deposit amount will be added.
     * @param amount  The amount to be deposited.
     */
    DepositOperation(BankAccount account, BigDecimal amount) {
        this.account = account;
        this.amount = amount;
    }

    /**
     * Executes the deposit operation on the associated {@link BankAccount}.
     * 
     * @return An {@link OperationResult} indicating the success of the deposit operation.
     */
    @Override
    public OperationResult execute() {
        this.account.deposit(this.amount);

        return new OperationResult(String.format(MESSAGE_DEPOSIT_SUCCESS, amount));
    }
}

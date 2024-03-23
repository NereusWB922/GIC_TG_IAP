package awesomegic.bank.model.account;

import static awesomegic.bank.utils.NumberUtils.checkPositive;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import awesomegic.bank.model.transaction.Transaction;
import awesomegic.bank.model.transaction.TransactionList;

/**
 * Represents an immutable bank account.
 */
public final class BankAccount {
    private static final String MESSAGE_INVALID_WITHDRAWAL_AMOUNT = "Withdrawal amount must not greater than balance.";
    private static final String MESSAGE_NON_POSITIVE_DEPOSIT_AMOUNT = "Deposit amount must be positive.";
    private static final String MESSAGE_NON_POSITIVE_WITHDRAWAL_AMOUNT = "Withdrawal amount must be positive.";

    private final BigDecimal balance;
    private final TransactionList transactions;
    
    /**
     * Constructs a new BankAccount object with a zero balance.
     */
    public BankAccount() {
        this.balance = BigDecimal.ZERO.setScale(2);
        this.transactions = new TransactionList();
    }

    /**
     * Constructs a new {@link BankAccount} object with the specified balance and transaction list.
     *
     * @param balance The initial balance of the bank account.
     * @param transactions The list of transactions associated with the bank account.
     */
    public BankAccount(BigDecimal balance, TransactionList transactions) {
        this.balance = balance;
        this.transactions = transactions;
    }

    /**
     * Deposits the specified amount into the bank account.
     *
     * @param amount The amount to be deposited.
     * @return A {@link BankAccount} with the updated balance and transaction list.
     * @throws IllegalArgumentException if the amount is not positive.
     */
    public BankAccount deposit(BigDecimal amount) {
        checkPositive(amount, MESSAGE_NON_POSITIVE_DEPOSIT_AMOUNT);

        BigDecimal newBalance = this.balance.add(amount);

        Transaction transaction = new Transaction(amount, newBalance, LocalDateTime.now());
        TransactionList newTransactionsList = transactions.add(transaction);

        return new BankAccount(newBalance, newTransactionsList);
    }

    /**
     * Withdraws the specified amount from the bank account.
     *
     * @param amount The amount to be withdrawn.
     * @return A {@link BankAccount} with the updated balance and transaction list.
     * @throws IllegalArgumentException if the amount is not positive or exceeds the balance.
     */
    public BankAccount withdraw(BigDecimal amount) {
        checkPositive(amount, MESSAGE_NON_POSITIVE_WITHDRAWAL_AMOUNT);

        if (!this.isBalanceSufficient(amount)) {
            throw new IllegalArgumentException(MESSAGE_INVALID_WITHDRAWAL_AMOUNT);
        }

        BigDecimal newBalance = this.balance.subtract(amount);
        
        Transaction transaction = new Transaction(amount.negate(), newBalance, LocalDateTime.now());
        TransactionList newTransactionsList = transactions.add(transaction);

        return new BankAccount(newBalance, newTransactionsList);
    }

    /**
     * Checks if the balance is sufficient for a withdrawal of the specified amount.
     *
     * @param withdrawalAmount The amount to be withdrawn.
     * @return {@code true} if the balance is sufficient, {@code false} otherwise.
     * @throws IllegalArgumentException if the amount is not positive.
     */
    public boolean isBalanceSufficient(BigDecimal withdrawalAmount) {
        checkPositive(withdrawalAmount, MESSAGE_NON_POSITIVE_WITHDRAWAL_AMOUNT);
        return this.balance.compareTo(withdrawalAmount) >= 0;
    }

    /**
     * Generates an account statement with the current list of transactions.
     *
     * @return An {@link AccountStatement} object representing the account statement.
     */
    public AccountStatement generateStatement() {
        return new AccountStatement(this.transactions);
    }
}

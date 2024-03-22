package awesomegic.bank.model.account;

import static awesomegic.bank.utils.NumberUtils.checkPositive;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import awesomegic.bank.model.transaction.Transaction;
import awesomegic.bank.model.transaction.TransactionList;

/**
 * Represents a bank account.
 */
public class BankAccount {
    private static final String MESSAGE_INVALID_WITHDRAWAL_AMOUNT = "Withdrawal amount must not greater than balance.";
    private static final String MESSAGE_NON_POSITIVE_DEPOSIT_AMOUNT = "Deposit amount must be positive.";
    private static final String MESSAGE_NON_POSITIVE_WITHDRAWAL_AMOUNT = "Withdrawal amount must be positive.";

    private BigDecimal balance;
    private TransactionList transactions;
    
    /**
     * Constructs a new BankAccount object with a zero balance.
     */
    public BankAccount() {
        this.balance = BigDecimal.ZERO.setScale(2);
        this.transactions = new TransactionList();
    }

    /**
     * Deposits the specified amount into the bank account.
     *
     * @param amount The amount to be deposited.
     * @throws IllegalArgumentException if the amount is not positive.
     */
    public void deposit(BigDecimal amount) {
        checkPositive(amount, MESSAGE_NON_POSITIVE_DEPOSIT_AMOUNT);
        this.balance = this.balance.add(amount);
        transactions.add(new Transaction(amount, this.balance, LocalDateTime.now()));
    }

    /**
     * Withdraws the specified amount from the bank account.
     *
     * @param amount The amount to be withdrawn.
     * @throws IllegalArgumentException if the amount is not positive or exceeds the balance.
     */
    public void withdraw(BigDecimal amount) {
        checkPositive(amount, MESSAGE_NON_POSITIVE_WITHDRAWAL_AMOUNT);
        if (this.balance.compareTo(amount) < 0) {
            throw new IllegalArgumentException(MESSAGE_INVALID_WITHDRAWAL_AMOUNT);
        }
        this.balance = this.balance.subtract(amount);
        transactions.add(new Transaction(amount.negate(), this.balance, LocalDateTime.now()));
    }

    /**
     * Checks if the balance is sufficient for a withdrawal of the specified amount.
     *
     * @param withdrawalAmount The amount to be withdrawn.
     * @return true if the balance is sufficient, false otherwise.
     * @throws IllegalArgumentException if the amount is not positive.
     */
    public boolean isBalanceSufficient(BigDecimal withdrawalAmount) {
        checkPositive(withdrawalAmount, MESSAGE_NON_POSITIVE_WITHDRAWAL_AMOUNT);
        return this.balance.compareTo(withdrawalAmount) >= 0;
    }

    /**
     * Generates an account statement with current transactions list.
     */
    public AccountStatement generateStatement() {
        return new AccountStatement(this.transactions);
    }
}

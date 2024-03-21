package awesomegic.bank.model.account;

import static awesomegic.bank.utils.NumberUtils.checkPositive;

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

    private double balance;
    private TransactionList transactions;
    
    /**
     * Constructs a new BankAccount object with a zero balance.
     */
    public BankAccount() {
        this.balance = 0;
        this.transactions = new TransactionList();
    }

    /**
     * Deposits the specified amount into the bank account.
     *
     * @param amount The amount to be deposited.
     * @throws IllegalArgumentException if the amount is not positive.
     */
    public void deposit(double amount) {
        checkPositive(amount, MESSAGE_NON_POSITIVE_DEPOSIT_AMOUNT);
        this.balance += amount;
        transactions.add(new Transaction(amount, this.balance, LocalDateTime.now()));
    }

    /**
     * Withdraws the specified amount from the bank account.
     *
     * @param amount The amount to be withdrawn.
     * @throws IllegalArgumentException if the amount is not positive or exceeds the balance.
     */
    public void withdraw(double amount) {
        checkPositive(amount, MESSAGE_NON_POSITIVE_WITHDRAWAL_AMOUNT);
        if (amount > this.balance) {
            throw new IllegalArgumentException(MESSAGE_INVALID_WITHDRAWAL_AMOUNT);
        }
        this.balance -= amount;
        transactions.add(new Transaction(-1 * amount, this.balance, LocalDateTime.now()));
    }

    /**
     * Checks if the balance is sufficient for a withdrawal of the specified amount.
     *
     * @param withdrawalAmount The amount to be withdrawn.
     * @return true if the balance is sufficient, false otherwise.
     * @throws IllegalArgumentException if the amount is not positive.
     */
    public boolean isBalanceSufficient(double withdrawalAmount) {
        checkPositive(withdrawalAmount, MESSAGE_NON_POSITIVE_WITHDRAWAL_AMOUNT);
        return this.balance >= withdrawalAmount;
    }

    /**
     * Generates an account statement with current transactions list.
     */
    public AccountStatement generateStatement() {
        return new AccountStatement(this.transactions);
    }
}

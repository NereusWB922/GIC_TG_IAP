package awesomegic.bank.model.transaction;

import static java.util.Objects.requireNonNull;
import static awesomegic.bank.utils.NumberUtils.checkNonNegative;

import java.time.LocalDateTime;

/**
 * Represents a transaction in a bank system.
 */
public class Transaction {
    private static final String MESSAGE_NEGATIVE_BALANCE = "Account balance must be non-negative.";
    public final double amount;
    public final double balance;
    public final LocalDateTime dataTime;

    /**
     * Constructs a new Transaction object.
     *
     * @param amount   The amount involved in the transaction.
     * @param balance  The balance after the transaction.
     * @param dateTime The date and time of the transaction.
     */
    public Transaction(double amount, double balance, LocalDateTime dateTime) {
        checkNonNegative(balance, MESSAGE_NEGATIVE_BALANCE);
        requireNonNull(dateTime);
        this.amount = amount;
        this.balance = balance;
        this.dataTime = dateTime;
    }
}

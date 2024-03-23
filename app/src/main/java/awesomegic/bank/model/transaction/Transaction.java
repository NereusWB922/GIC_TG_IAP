package awesomegic.bank.model.transaction;

import static awesomegic.bank.cli.Message.MESSAGE_NEGATIVE_BALANCE;
import static awesomegic.bank.cli.Message.MESSAGE_NON_ZERO_TRANSACTION_AMOUNT;
import static awesomegic.bank.utils.CollectionUtil.requireAllNonNull;
import static awesomegic.bank.utils.NumberUtils.requireNonNegative;
import static awesomegic.bank.utils.NumberUtils.requireNonZero;

import java.math.BigDecimal;

import java.time.LocalDateTime;

/**
 * Represents a transaction in a bank system.
 */
public class Transaction {
    public final BigDecimal amount;
    public final BigDecimal balance;
    public final LocalDateTime dataTime;

    /**
     * Constructs a new Transaction object.
     *
     * @param amount   The amount involved in the transaction.
     * @param balance  The balance after the transaction.
     * @param dateTime The date and time of the transaction.
     */
    public Transaction(BigDecimal amount, BigDecimal balance, LocalDateTime dateTime) {
        requireAllNonNull(amount, balance, dateTime);
        requireNonNegative(balance, MESSAGE_NEGATIVE_BALANCE);
        requireNonZero(amount, MESSAGE_NON_ZERO_TRANSACTION_AMOUNT);

        this.amount = amount;
        this.balance = balance;
        this.dataTime = dateTime;
    }
}

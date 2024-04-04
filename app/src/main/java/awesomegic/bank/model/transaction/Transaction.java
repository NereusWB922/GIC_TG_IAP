package awesomegic.bank.model.transaction;

import static awesomegic.bank.cli.Message.MESSAGE_NEGATIVE_BALANCE;
import static awesomegic.bank.cli.Message.MESSAGE_NON_ZERO_TRANSACTION_AMOUNT;
import static awesomegic.bank.utils.CollectionUtil.requireAllNonNull;
import static awesomegic.bank.utils.NumberUtils.requireNonNegative;
import static awesomegic.bank.utils.NumberUtils.requireNonZero;

import java.math.BigDecimal;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Represents a transaction in a bank system.
 */
public final class Transaction {
    public final BigDecimal amount;
    public final BigDecimal balance;
    public final LocalDateTime dateTime;

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
        this.dateTime = dateTime;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Transaction)) {
            return false;
        }

        Transaction other = (Transaction) obj;

        return this.amount.equals(other.amount)
            && this.balance.equals(other.balance)
            && this.dateTime.equals(other.dateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            this.amount.hashCode(),
            this.balance.hashCode(),
            this.dateTime.hashCode());
    }
}

package awesomegic.bank.model.transaction;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Represents an immutable transactions list.
 */
public final class TransactionList implements ReadOnlyTransactionList {
    private final List<Transaction> transactions;

    /**
     * Constructs an empty {@code TransactionList}.
     */
    public TransactionList() {
        this.transactions = new ArrayList<>();
    }

    /**
     * Constructs a {@code TransactionList} with the given transactions.
     * 
     * @param transactions The list of transactions to initialize the {@code TransactionList} with.
     */
    public TransactionList(List<Transaction> transactions) {
        requireNonNull(transactions);

        this.transactions = new ArrayList<>(transactions);
    }

    /**
     * Adds a transaction to the list.
     * 
     * @param transaction The transaction to add. Must not be {@code null}.
     * @return A new {@code TransactionList} containing the added transaction.
     * @throws NullPointerException if the provided transaction is {@code null}.
     */
    public TransactionList add(Transaction transaction) {
        requireNonNull(transaction);

        List<Transaction> newList = new ArrayList<>(this.transactions);
        newList.add(transaction);

        return new TransactionList(newList);
    }

    @Override
    public boolean isEmpty() {
        return transactions.isEmpty();
    }
    
    @Override
    public Iterator<Transaction> iterator() {
        return transactions.iterator();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof TransactionList)) {
            return false;
        }

        TransactionList other = (TransactionList) obj;

        return this.transactions.equals(other.transactions);
    }

    @Override
    public int hashCode() {
        return this.transactions.hashCode();
    }
}

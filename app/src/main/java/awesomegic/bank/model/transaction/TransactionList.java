package awesomegic.bank.model.transaction;

import static java.util.Objects.requireNonNull;

/**
 * Represents a mutable list of transactions.
 */
public class TransactionList extends ReadOnlyTransactionList {
    public TransactionList() {
        super();
    }

    /**
     * Adds a transaction to the list.
     */
    public void add(Transaction t) {
        requireNonNull(t);
        this.transactions.add(t);
    }
}

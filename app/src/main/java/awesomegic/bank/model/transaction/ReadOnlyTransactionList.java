package awesomegic.bank.model.transaction;

/**
 * Represents a read-only list of transactions.
 */
public interface ReadOnlyTransactionList extends Iterable<Transaction> {
    /**
     * Checks if the transaction list is empty.
     */
    boolean isEmpty();
}

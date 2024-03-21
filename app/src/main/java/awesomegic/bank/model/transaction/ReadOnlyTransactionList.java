package awesomegic.bank.model.transaction;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Represents a read-only list of transactions.
 */
public class ReadOnlyTransactionList implements Iterable<Transaction> {
    protected List<Transaction> transactions;

    /**
     * Constructs an empty read-only transaction list.
     */
    public ReadOnlyTransactionList() {
        this.transactions = new ArrayList<>();
    }

    /**
     * Checks if the transaction list is empty.
     */
    public boolean isEmpty() {
        return transactions.isEmpty();
    }

    @Override
    public Iterator<Transaction> iterator() {
        return transactions.iterator();
    }
}

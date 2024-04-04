package awesomegic.bank.model.account;

import static java.util.Objects.requireNonNull;

import java.time.format.DateTimeFormatter;

import awesomegic.bank.model.transaction.ReadOnlyTransactionList;
import awesomegic.bank.model.transaction.Transaction;

/**
 * Represents an account statement for a bank account.
 */
public class AccountStatement {
    private static final String NO_TRANSACTIONS_FOUND = "No Transactions Found!";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy h:mm:ssa");

    private final ReadOnlyTransactionList transactions;
    private int dateColumnWidth = 4;
    private int amountColumnWidth = 10;
    private int balanceColumnWidth = 10;

    /**
     * Constructs a new AccountStatement object with the specified list of transactions.
     *
     * @param transactions The list of transactions associated with the account statement.
     */
    AccountStatement(ReadOnlyTransactionList transactions) {
        requireNonNull(transactions);

        this.transactions = transactions;

        for (Transaction transaction : this.transactions) {
            this.dateColumnWidth = Math.max(this.dateColumnWidth, transaction.dateTime.format(formatter).length() + 2);
            this.amountColumnWidth = Math.max(this.amountColumnWidth, String.valueOf(transaction.amount).length() + 2);
            this.balanceColumnWidth = Math.max(this.balanceColumnWidth, String.valueOf(transaction.balance).length() + 2);
        }
    }

    /**
     * Returns a string representation of the account statement.
     *
     * @return A formatted table displaying transaction details if transaction list is not empty,
     * otherwise, a message indicating no transactions were found.
     */
    @Override
    public String toString() {
        if (this.transactions.isEmpty()) {
            return NO_TRANSACTIONS_FOUND;
        }
        return getTransactionTable();
    }

    private String centeredCell(String str, int width) {
        int leftPadding = (width - str.length() + 1) / 2;
        int rightPadding = (width - str.length()) / 2;
        String format = "%" + (leftPadding == 0 ? "" : leftPadding) + "s%s%" + (rightPadding == 0 ? "" : rightPadding) + "s";
        return String.format(format, "", str, "");
    }

    private String getRow(String date, String amount, String balance) {
        String[] list = new String[]{
            centeredCell(date, dateColumnWidth),
            centeredCell(amount, amountColumnWidth),
            centeredCell(balance, balanceColumnWidth),
        };
        return String.join("|", list);
    }

    private String getTransactionTable() {
        StringBuilder sb = new StringBuilder();
        sb.append(getRow("Date", "Amount", "Balance"));

        for (Transaction t : transactions) {
            sb.append("\n");
            sb.append(getRow(t.dateTime.format(formatter), String.valueOf(t.amount), String.valueOf(t.balance)));
        }

        return sb.toString();
    }
}

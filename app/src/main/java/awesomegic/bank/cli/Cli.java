package awesomegic.bank.cli;

import java.util.Scanner;

import awesomegic.bank.cli.exceptions.InputException;

/**
 * Handles command-line interactions for the application.
 */
public class Cli {
    private static final String MESSAGE_PROMPT_AMOUNT = "Please enter the amount to %s:";
    private static final String MESSAGE_INVALID_INPUT = "Invalid number format.";
    private static final String MESSAGE_NON_POSITIVE_TRANSACTION_AMOUNT = "Transaction amount must be positive.";
    private final Scanner scanner;

    /**
     * Initializes a new CLI instance.
     */
    public Cli() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads and validates the transaction amount from the CLI.
     *
     * @param transactionType A {@link String} describing the type of transaction (e.g., "deposit", "withdraw").
     * @return A {@code double} representing the validated transaction amount.
     * @throws InputException If the input is not a valid number or is non-positive.
     */
    public double readTransactionAmount(String transactionType) throws InputException {
        System.out.println(String.format(MESSAGE_PROMPT_AMOUNT, transactionType));

        String input = this.scanner.nextLine();
        double amount;
        try {
            amount = Double.parseDouble(input);
        } catch (NumberFormatException e) {
            throw new InputException(MESSAGE_INVALID_INPUT);
        }

        if (amount <= 0) {
            throw new InputException(MESSAGE_NON_POSITIVE_TRANSACTION_AMOUNT);
        }

        return amount;
    }

    /**
     * Closes the CLI.
     */
    public void close() {
        this.scanner.close();
    }
}

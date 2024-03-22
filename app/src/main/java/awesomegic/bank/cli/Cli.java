package awesomegic.bank.cli;

import java.util.Scanner;

import awesomegic.bank.cli.exceptions.InputException;
import awesomegic.bank.operation.OperationFactory;
import awesomegic.bank.operation.OperationResult;
import awesomegic.bank.operation.exceptions.OperationException;

/**
 * Handles command-line interactions for the application.
 */
public class Cli {
    private static final String MESSAGE_PROMPT_AMOUNT = "Please enter the amount to %s:";
    private static final String MESSAGE_INVALID_INPUT = "Invalid number format.";
    private static final String MESSAGE_NON_POSITIVE_TRANSACTION_AMOUNT = "Transaction amount must be positive.";
    private static final String MESSAGE_MORE_THAN_TWO_DECIMAL_PLACES = "Transaction amount must have at most two decimal places.";
    private static final String MESSAGE_WELCOME = "Welcome to AwesomeGIC Bank! What would you like to do?";
    private static final String MESSAGE_EXIT = "Thank you for banking with AwesomeGIC Bank.\nHave a nice day!";
    private static final String MESSAGE_OPTIONS = "[D]eposit\n[W]ithdraw\n[P]rint statement\n[Q]uit";
    private static final String MESSAGE_INVALID_OPTION = "Please enter a valid option (D/W/P/Q).";
    private static final String MESSAGE_INPUT_ERROR = "Invalid input: %s";
    private static final String MESSAGE_OPERATION_ERROR = "Invalid operation: %s";
    private static final String MESSAGE_PROMPT_NEW_OPERATION = "Is there anything else you'd like to do?";
    private final Scanner scanner;

    /**
     * Initializes a new CLI instance.
     */
    public Cli() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads the user input.
     */
    public String readInput() {
        return this.scanner.nextLine();
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

    }

    /**
     * Reads and validates the user's operation option.
     *
     * @param factory An {@link OperationFactory} instance.
     * @return A {@code String} representing the user's operation option.
     * @throws InputException If the input is not a valid operation option.
     */
    public String readOperationOption(OperationFactory factory) throws InputException {
        String input = this.readInput();
        String formattedInput = input.trim().toLowerCase();
        if (!factory.containsKey(formattedInput)) {
            throw new InputException(MESSAGE_INVALID_OPTION);
        }

        return formattedInput;
    }

    /**
     * Displays a message to the user.
     *
     * @param msg A {@code String} message to be displayed.
     */
    public void show(String msg) {
        System.out.println(msg);
    }

    /**
     * Displays an error message for invalid input.
     *
     * @param exception An {@link InputException} instance.
     */
    public void showInputError(InputException exception) {
        this.show(String.format(MESSAGE_INPUT_ERROR, exception.getMessage()));
    }

    /**
     * Displays an error message for invalid operation.
     *
     * @param exception An {@link OperationException} instance.
     */
    public void showOperationError(OperationException exception) {
        this.show(String.format(MESSAGE_OPERATION_ERROR, exception.getMessage()));
    }

    /**
     * Prompts the user for a new operation.
     */
    public void showNewOperationPrompt() {
        this.show(MESSAGE_PROMPT_NEW_OPERATION);
        this.show(MESSAGE_OPTIONS);
    }

    /**
     * Displays the welcome message and available options to the user.
     */
    public void showStartupMessage() {
        this.show(MESSAGE_WELCOME);
        this.show(MESSAGE_OPTIONS);
    }

    /**
     * Displays the exit message to the user.
     */
    public void showExitMessage() {
        this.show(MESSAGE_EXIT);
    }
    
    /**
     * Displays the result of an operation to the user.
     *
     * @param result An {@link OperationResult} instance representing the result of an operation.
     */
    public void showResult(OperationResult result) {
        this.show(result.feedback);
    }

    /**
     * Closes the CLI.
     */
    public void close() {
        this.scanner.close();
    }
}

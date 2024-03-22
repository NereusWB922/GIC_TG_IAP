package awesomegic.bank.operation;

import java.util.HashMap;
import java.util.Map;

import awesomegic.bank.cli.Cli;
import awesomegic.bank.model.account.BankAccount;

/**
 * Factory class for creating operations in the bank system.
 */
public class OperationFactory {
    private static final String MESSAGE_INVALID_KEY = "The key '%s' is not recognized as a valid key for an operation.";
    private static final Operation QUIT_OPERATION = new QuitOperation();
    private final BankAccount account;
    private final Cli cli;
    private final Map<String, Operation> operations;

    /**
     * Constructs a new OperationFactory with the specified bank account and CLI interface.
     *
     * @param account The {@link BankAccount} to perform operations on.
     * @param cli The {@link Cli} instance used to interact with the user.
     */
    public OperationFactory(BankAccount account, Cli cli) {
        this.account = account;
        this.cli = cli;
        this.operations = new HashMap<>();
        this.init();
    }

    /**
     * Initializes the operation map with predefined operation implementations.
     */
    private void init() {
        this.operations.put(DepositOperation.OPERATION_KEY, new DepositOperation(this.account, this.cli));
        this.operations.put(WithdrawOperation.OPERATION_KEY, new WithdrawOperation(this.account, this.cli));
        this.operations.put(PrintStatementOperation.OPERATION_KEY, new PrintStatementOperation(this.account));
        this.operations.put(QuitOperation.OPERATION_KEY, QUIT_OPERATION);
    }

    /**
     * Retrieves the operation associated with the given key.
     *
     * @param key The key representing the desired operation.
     * @return The operation associated with the given key.
     * @throws IllegalArgumentException If the key is not recognized.
     */
    public Operation getOperation(String key) {
        if (!containsKey(key)) {
            throw new IllegalArgumentException(String.format(MESSAGE_INVALID_KEY, key));
        }
        return this.operations.get(key);
    }

    /**
     * Checks if the factory contains an operation with the given key.
     *
     * @param key The key to check for.
     * @return {@code true} if the factory contains the key, {@code false} otherwise.
     */
    public boolean containsKey(String key) {
        return this.operations.containsKey(key);
    }
}

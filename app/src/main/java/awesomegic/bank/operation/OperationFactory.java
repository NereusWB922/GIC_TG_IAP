package awesomegic.bank.operation;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import awesomegic.bank.cli.Cli;
import awesomegic.bank.cli.exceptions.InputException;

/**
 * Factory class for creating operations in the bank system.
 */
public class OperationFactory {
    private static final String MESSAGE_INVALID_KEY = "The key '%s' is not recognized as a valid key for an operation.";
    private static final Operation QUIT_OPERATION = new QuitOperation();
    private final Cli cli;
    private final Set<String> operationKeys;

    /**
     * Constructs a new OperationFactory with the CLI interface.
     *
     * @param cli The {@link Cli} instance used to interact with the user.
     */
    public OperationFactory(Cli cli) {
        this.cli = cli;
        this.operationKeys = new HashSet<>();
        this.init();
    }

    /**
     * Initializes the operation keys set.
     */
    private void init() {
        this.operationKeys.add(DepositOperation.OPERATION_KEY);
        this.operationKeys.add(WithdrawOperation.OPERATION_KEY);
        this.operationKeys.add(PrintStatementOperation.OPERATION_KEY);
        this.operationKeys.add(QuitOperation.OPERATION_KEY);
    }

    /**
     * Retrieves the operation associated with the given key.
     *
     * @param key The key representing the desired operation.
     * @return The {@link Operation} associated with the given key.
     * @throws IllegalArgumentException If the key is not recognized.
     */
    public Operation getOperation(String key) throws InputException {
        switch (key) {
        case DepositOperation.OPERATION_KEY:
            BigDecimal depositAmount = this.cli.readTransactionAmount(DepositOperation.TRANSACTION_TYPE);
            return new DepositOperation(depositAmount);
        case WithdrawOperation.OPERATION_KEY:
            BigDecimal withdrawalAmount = this.cli.readTransactionAmount(WithdrawOperation.TRANSACTION_TYPE);
            return new WithdrawOperation(withdrawalAmount);
        case PrintStatementOperation.OPERATION_KEY:
            return new PrintStatementOperation();
        case QuitOperation.OPERATION_KEY:
            return QUIT_OPERATION;
        default:
            throw new IllegalArgumentException(String.format(MESSAGE_INVALID_KEY, key));
        }
    }

    /**
     * Checks if the factory contains an operation with the given key.
     *
     * @param key The key to check for.
     * @return {@code true} if the factory contains the key, {@code false} otherwise.
     */
    public boolean containsKey(String key) {
        return this.operationKeys.contains(key);
    }
}

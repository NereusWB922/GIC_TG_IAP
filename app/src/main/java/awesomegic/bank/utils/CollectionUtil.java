package awesomegic.bank.utils;

import static java.util.Objects.requireNonNull;

import java.util.Objects;
import java.util.stream.Stream;

public class CollectionUtil {

    /**
     * Throws NullPointerException if {@code items} or any element of {@code items} is null.
     */
    public static void requireAllNonNull(Object... items) {
        requireNonNull(items);
        Stream.of(items).forEach(Objects::requireNonNull);
    }
}

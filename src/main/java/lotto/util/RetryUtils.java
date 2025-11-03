package lotto.util;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class RetryUtils {
    private RetryUtils() {
    }

    public static <T> T retryOnInvalidInput(Supplier<T> retryableAction, Consumer< IllegalArgumentException> errorHandler) {
        try {
            return retryableAction.get();
        } catch (IllegalArgumentException e) {
            errorHandler.accept(e);

            return retryOnInvalidInput(retryableAction, errorHandler);
        }
    }
}

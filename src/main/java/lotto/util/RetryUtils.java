package lotto.util;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class RetryUtils {
    private RetryUtils() {
    }

    public static <T> T retryIfIllegalArgument(Supplier<T> action, Consumer< IllegalArgumentException> errorHandler) {
        try {
            return action.get();
        } catch (IllegalArgumentException e) {
            errorHandler.accept(e);

            return retryIfIllegalArgument(action, errorHandler);
        }
    }
}

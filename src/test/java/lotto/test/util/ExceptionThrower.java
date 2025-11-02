package lotto.test.util;

import java.util.function.Supplier;

public class ExceptionThrower {
    private final int maxThrowCount;
    private int thrownCount = 0;
    private final Supplier<? extends RuntimeException> exceptionSupplier;

    public ExceptionThrower(int maxThrowCount, Supplier<? extends RuntimeException> exceptionSupplier) {
        this.maxThrowCount = maxThrowCount;
        this.exceptionSupplier = exceptionSupplier;
    }

    public int action() {
        if (thrownCount < maxThrowCount) {
            thrownCount++;
            throw exceptionSupplier.get();
        }

        return thrownCount;
    }

    public int getThrownCount() {
        return thrownCount;
    }
}

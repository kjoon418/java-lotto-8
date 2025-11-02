package lotto.test.util;

import java.util.function.Consumer;

public class CountingConsumer<T> implements Consumer<T> {
    private int callCount = 0;

    @Override
    public void accept(T t) {
        callCount++;
    }

    public int getCallCount() {
        return callCount;
    }
}

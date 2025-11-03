package lotto.util;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.function.Consumer;
import lotto.test.util.CountingConsumer;
import lotto.test.util.ExceptionThrower;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class RetryUtilsTest {
    private static final Consumer<IllegalArgumentException> DEFAULT_EXCEPTION_CONSUMER = (e) -> {};

    @ParameterizedTest
    @ValueSource(ints = {1, 3, 5, 10, 100})
    void 동작_중_예외가_발생하면_다시_시도한다(int throwCount) {
        // given: throwCount번 재시도 할 때 까지 예외가 발생하도록 설정
        ExceptionThrower exceptionThrower = new ExceptionThrower(throwCount, IllegalArgumentException::new);

        // when
        RetryUtils.retryOnInvalidInput(exceptionThrower::action, DEFAULT_EXCEPTION_CONSUMER);

        // then
        assertThat(exceptionThrower.getThrownCount()).isEqualTo(throwCount);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 3, 5, 10, 100})
    void 예외가_발생하면_Consumer를_호출한다(int throwCount) {
        // given: throwCount번 재시도 할 때 까지 예외가 발생하도록 설정
        ExceptionThrower exceptionThrower = new ExceptionThrower(throwCount, IllegalArgumentException::new);
        CountingConsumer<IllegalArgumentException> countingConsumer = new CountingConsumer<>();

        // when
        RetryUtils.retryOnInvalidInput(exceptionThrower::action, countingConsumer);

        // then: Consumer의 호출 횟수가 예외 발생 횟수와 같은지 검증
        assertThat(countingConsumer.getCallCount()).isEqualTo(throwCount);
    }
}

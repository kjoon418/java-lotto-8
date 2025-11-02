package lotto.model.lotto;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottoNumberTest {
    @ParameterizedTest
    @ValueSource(ints = {0, -10, Integer.MIN_VALUE})
    void 값이_1보다_작으면_예외를_던진다(int illegalValue) {
        Assertions.assertThatThrownBy(() -> new LottoNumber(illegalValue))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {46, 100, Integer.MAX_VALUE})
    void 값이_45보다_크면_예외를_던진다(int illegalValue) {
        Assertions.assertThatThrownBy(() -> new LottoNumber(illegalValue))
                .isInstanceOf(IllegalArgumentException.class);
    }
}

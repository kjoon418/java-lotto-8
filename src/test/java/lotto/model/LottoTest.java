package lotto.model;

import static lotto.test.util.TestUtils.lottoNumbersOf;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottoTest {
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 7, 8, 9, 10})
    void 로또_번호의_개수가_6개보다_작거나_크다면_예외가_발생한다(int numberAmount) {
        List<LottoNumber> numbers = createNumbers(numberAmount);

        assertThatThrownBy(() -> new Lotto(numbers))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 로또_번호에_중복된_숫자가_있으면_예외가_발생한다() {
        List<LottoNumber> lottoNumbers = lottoNumbersOf(1, 2, 3, 4, 5, 5);

        assertThatThrownBy(() -> new Lotto(lottoNumbers))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private List<LottoNumber> createNumbers(int size) {
        return Stream.iterate(1, number -> number + 1)
                .limit(size)
                .map(LottoNumber::new)
                .toList();
    }
}

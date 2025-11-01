package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class WinningLottoTest {
    private static final int DEFAULT_BONUS_NUMBER = 30;
    private static final List<Integer> DEFAULT_NUMBERS = List.of(1, 2, 3, 4, 5, 6);

    @Test
    void 보너스_번호가_다른_번호와_중복되면_예외를_던진다() {
        // given
        int bonusNumber = 6;
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);

        // when & then
        assertThatThrownBy(() -> new WinningLotto(numbers, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Nested
    class 다른_로또의_번호가_보너스_번호를_포함하고_있는지_여부를_반환한다 {
        @Test
        void 포함하는_경우() {
            // given
            WinningLotto winningLotto = new WinningLotto(DEFAULT_NUMBERS, DEFAULT_BONUS_NUMBER);
            Lotto otherLotto = new Lotto(List.of(1, 2, 3, 4, 5, DEFAULT_BONUS_NUMBER));

            // when
            boolean bonusNumberMatch = winningLotto.bonusNumberMatchedWith(otherLotto);

            // then
            assertThat(bonusNumberMatch).isTrue();
        }

        @Test
        void 포함하지_않는_경우() {
            // given
            WinningLotto winningLotto = new WinningLotto(DEFAULT_NUMBERS, DEFAULT_BONUS_NUMBER);
            Lotto otherLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

            // when
            boolean bonusNumberMatch = winningLotto.bonusNumberMatchedWith(otherLotto);

            // then
            assertThat(bonusNumberMatch).isFalse();
        }
    }
}

package lotto.model;

import static lotto.test.util.TestUtils.lottoNumbersOf;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class WinningLottoTest {
    private static final LottoNumber DEFAULT_BONUS_NUMBER = new LottoNumber(30);
    private static final Lotto DEFAULT_LOTTO = new Lotto(lottoNumbersOf(1, 2, 3, 4, 5, 6));

    @Test
    void 보너스_번호가_다른_번호와_중복되면_예외를_던진다() {
        // given
        LottoNumber bonusNumber = new LottoNumber(6);
        Lotto lotto = new Lotto(lottoNumbersOf(1, 2, 3, 4, 5, 6));

        // when & then
        assertThatThrownBy(() -> new WinningLotto(lotto, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Nested
    class 다른_로또의_번호가_보너스_번호를_포함하고_있는지_여부를_반환한다 {
        @Test
        void 포함하는_경우() {
            // given
            WinningLotto winningLotto = new WinningLotto(DEFAULT_LOTTO, DEFAULT_BONUS_NUMBER);
            Lotto otherLotto = new Lotto(lottoNumbersOf(1, 2, 3, 4, 5, DEFAULT_BONUS_NUMBER.get()));

            // when
            boolean bonusNumberMatch = winningLotto.bonusNumberMatchedWith(otherLotto);

            // then
            assertThat(bonusNumberMatch).isTrue();
        }

        @Test
        void 포함하지_않는_경우() {
            // given
            WinningLotto winningLotto = new WinningLotto(DEFAULT_LOTTO, DEFAULT_BONUS_NUMBER);
            Lotto otherLotto = new Lotto(lottoNumbersOf(1, 2, 3, 4, 5, 6));

            // when
            boolean bonusNumberMatch = winningLotto.bonusNumberMatchedWith(otherLotto);

            // then
            assertThat(bonusNumberMatch).isFalse();
        }
    }
}

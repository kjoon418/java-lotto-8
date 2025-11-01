package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottoResultCalculatorImplTest {
    private static final int LOTTO_SIZE = 6;
    private static final int[] WINNING_NUMBERS = {1, 2, 3, 4, 5, 6};
    private static final int[] WRONG_NUMBERS = {11, 12, 13, 14, 15, 16};
    private static final int BONUS_NUMBER = 7;
    private static final WinningLotto WINNING_LOTTO = new WinningLotto(
            List.of(
                    WINNING_NUMBERS[0],
                    WINNING_NUMBERS[1],
                    WINNING_NUMBERS[2],
                    WINNING_NUMBERS[3],
                    WINNING_NUMBERS[4],
                    WINNING_NUMBERS[5]
            ),
            BONUS_NUMBER
    );

    private final LottoResultCalculator lottoResultCalculator = new LottoResultCalculatorImpl();

    @Nested
    class 로또의_당첨_결과를_계산한다 {
        @ParameterizedTest
        @ValueSource(ints = {0, 1, 2})
        void 당첨되지_않은_경우(int equalCount) {
            // given
            List<Integer> notWinningNumbers = createLottoNumbers(equalCount);
            Lotto notWinningLotto = new Lotto(notWinningNumbers);

            // when
            LottoResult result = lottoResultCalculator.calculate(notWinningLotto, WINNING_LOTTO);

            // then
            assertThat(result).isSameAs(LottoResult.NO_PRIZE);
        }

        @ParameterizedTest
        @ValueSource(ints = {3, 4, 5, 6})
        void 보너스_번호_없이_당첨된_경우(int equalCount) {
            // given
            List<Integer> threeEqualNumbers = createLottoNumbers(equalCount, false);
            Lotto threeEqualLotto = new Lotto(threeEqualNumbers);
            LottoResult expectedResult = LottoResult.of(equalCount, false);

            // when
            LottoResult actualResult = lottoResultCalculator.calculate(threeEqualLotto, WINNING_LOTTO);

            // then
            assertThat(actualResult).isSameAs(expectedResult);
        }

        @ParameterizedTest
        @ValueSource(ints = {3, 4, 5, 6})
        void 보너스_번호와_함께_당첨된_경우(int equalCount) {
            // given
            List<Integer> fiveBonusEqualNumbers = createLottoNumbers(equalCount, true);
            Lotto fiveBonusEqualLotto = new Lotto(fiveBonusEqualNumbers);
            LottoResult expectedResult = LottoResult.of(equalCount, true);

            // when
            LottoResult actualResult = lottoResultCalculator.calculate(fiveBonusEqualLotto, WINNING_LOTTO);

            // then
            assertThat(actualResult).isSameAs(expectedResult);
        }
    }

    private List<Integer> createLottoNumbers(int equalCount) {
        return createLottoNumbers(equalCount, false);
    }

    private List<Integer> createLottoNumbers(int equalCount, boolean bonusEqual) {
        List<Integer> numbers = new ArrayList<>();

        for (int i = 0; i < equalCount; i++) {
            numbers.add(WINNING_NUMBERS[i]);
        }
        if (bonusEqual && smallThanMaxSize(numbers)) {
            numbers.add(BONUS_NUMBER);
        }
        for (int i = 0; smallThanMaxSize(numbers); i++) {
            numbers.add(WRONG_NUMBERS[i]);
        }

        return numbers;
    }

    private boolean smallThanMaxSize(List<Integer> numbers) {
        return numbers.size() < LOTTO_SIZE;
    }
}

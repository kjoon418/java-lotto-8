package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import lotto.dto.LottoStatisticDto;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class LottoAnalyzerImplTest {
    private static final int PURCHASE_AMOUNT = 10_000;

    private final LottoAnalyzer lottoAnalyzer = new LottoAnalyzerImpl();

    @Nested
    class 당첨_종류별_로또의_개수를_계산한다 {
        @Test
        void 로또가_종류별로_한_장씩_있는_경우() {
            // given
            List<LottoResult> lottoResults = getEachTypeOfResult();

            // when
            LottoStatisticDto statistic = lottoAnalyzer.analyze(lottoResults, PURCHASE_AMOUNT);

            // then
            Map<LottoResult, Integer> results = statistic.resultAmounts();
            assertThat(results.values()
                    .stream()
                    .allMatch(amount -> amount == 1)
            ).isTrue();
        }
    }

    @Nested
    class 수익률을_계산한다 {
        @Test
        void 로또가_종류별로_한_장씩_있는_경우() {
            // given
            List<LottoResult> lottoResults = getEachTypeOfResult();
            double expectedProfitRate = (double) getTotalProfit(lottoResults) / PURCHASE_AMOUNT;

            // when
            LottoStatisticDto statistic = lottoAnalyzer.analyze(lottoResults, PURCHASE_AMOUNT);

            // then
            assertThat(statistic.profitRate()).isEqualTo(expectedProfitRate);
        }
    }

    private List<LottoResult> getEachTypeOfResult() {
        return Arrays.stream(LottoResult.values())
                .toList();
    }

    private int getTotalProfit(List<LottoResult> results) {
        return results.stream()
                .mapToInt(result -> result.prize)
                .sum();
    }
}

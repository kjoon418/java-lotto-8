package lotto.model.result;

import java.util.List;
import lotto.dto.LottoStatisticDto;

public class LottoAnalyzerImpl implements LottoAnalyzer {
    @Override
    public LottoStatisticDto analyze(List<LottoResult> results, int purchaseAmount) {
        ResultAmounts resultAmounts = calculateEachAmount(results);
        double profitRate = calculateProfitRate(results, purchaseAmount);

        return new LottoStatisticDto(resultAmounts.get(), profitRate);
    }

    private ResultAmounts calculateEachAmount(List<LottoResult> results) {
        List<LottoResult> prizeResults = filterNoPrizeResults(results);
        ResultAmounts resultAmounts = ResultAmounts.prizeResultsOnly();

        for (LottoResult result : prizeResults) {
            resultAmounts.increaseAmount(result);
        }

        return resultAmounts;
    }

    private List<LottoResult> filterNoPrizeResults(List<LottoResult> results) {
        return results.stream()
                .filter(LottoResult::hasPrize)
                .toList();
    }

    private double calculateProfitRate(List<LottoResult> results, int purchaseAmount) {
        int totalPrize = getTotalPrize(results);

        return (double) totalPrize / purchaseAmount;
    }

    private int getTotalPrize(List<LottoResult> results) {
        return results.stream()
                .mapToInt(LottoResult::getPrize)
                .sum();
    }
}

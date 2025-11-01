package lotto.model;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import lotto.dto.LottoStatisticDto;

public class LottoAnalyzerImpl implements LottoAnalyzer {
    @Override
    public LottoStatisticDto analyze(List<LottoResult> results, int purchaseAmount) {
        Map<LottoResult, Integer> resultAmounts = calculateEachAmount(results);
        double profitRate = calculateProfitRate(results, purchaseAmount);

        return new LottoStatisticDto(resultAmounts, profitRate);
    }

    private Map<LottoResult, Integer> calculateEachAmount(List<LottoResult> results) {
        return results.stream()
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.summingInt(e -> 1)
                ));
    }

    private double calculateProfitRate(List<LottoResult> results, int purchaseAmount) {
        int totalPrize = getTotalPrize(results);

        return (double) totalPrize / purchaseAmount;
    }

    private int getTotalPrize(List<LottoResult> results) {
        return results.stream()
                .mapToInt(result -> result.prize)
                .sum();
    }
}

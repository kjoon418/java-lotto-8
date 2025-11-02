package lotto.model;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class ResultAmounts {
    private static final int INITIAL_VALUE = 0;
    private static final int INCREASE_STEP = 1;

    private final EnumMap<LottoResult, Integer> resultAmounts;

    public ResultAmounts(List<LottoResult> resultTypes) {
        EnumMap<LottoResult, Integer> resultAmounts = new EnumMap<>(LottoResult.class);
        initializeAmounts(resultAmounts, resultTypes);

        this.resultAmounts = resultAmounts;
    }

    public static ResultAmounts prizeResultsOnly() {
        List<LottoResult> prizeResults = Arrays.stream(LottoResult.values())
                .filter(LottoResult::hasPrize)
                .toList();

        return new ResultAmounts(prizeResults);
    }

    public Map<LottoResult, Integer> get() {
        return Map.copyOf(resultAmounts);
    }

    public void increaseAmount(LottoResult key) {
        resultAmounts.merge(key, INCREASE_STEP, Integer::sum);
    }

    private void initializeAmounts(Map<LottoResult, Integer> resultAmounts, List<LottoResult> keys) {
        for (LottoResult key : keys) {
            resultAmounts.put(key, INITIAL_VALUE);
        }
    }
}

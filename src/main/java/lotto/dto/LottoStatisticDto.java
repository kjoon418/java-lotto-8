package lotto.dto;

import java.util.Map;
import lotto.model.LottoResult;

public record LottoStatisticDto(
        Map<LottoResult, Integer> resultAmounts,
        double profitRate
) {
}

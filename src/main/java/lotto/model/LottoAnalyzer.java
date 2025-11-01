package lotto.model;

import java.util.List;
import lotto.dto.LottoStatisticDto;

public interface LottoAnalyzer {
    LottoStatisticDto analyze(List<LottoResult> results, int purchaseAmount);
}

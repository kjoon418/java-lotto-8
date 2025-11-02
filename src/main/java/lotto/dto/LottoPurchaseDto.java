package lotto.dto;

import java.util.List;
import lotto.model.lotto.Lotto;

public record LottoPurchaseDto(
        List<Lotto> lottos,
        int usedPurchaseAmount
) {
}

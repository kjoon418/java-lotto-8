package lotto.dto;

import java.util.List;
import lotto.model.Lotto;

public record LottoPurchaseDto(
        List<Lotto> lottos,
        int usedPurchaseAmount
) {
}

package lotto.model.lotto;

import lotto.dto.LottoPurchaseDto;

public interface LottoPurchase {
    LottoPurchaseDto purchaseRandomLottos(int purchaseAmount);
}

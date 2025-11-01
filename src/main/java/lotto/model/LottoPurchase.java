package lotto.model;

import lotto.dto.LottoPurchaseDto;

public interface LottoPurchase {
    LottoPurchaseDto purchaseRandomLottos(int purchaseAmount);
}

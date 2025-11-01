package lotto.model;

import java.util.List;

public interface LottoPurchase {
    List<Lotto> purchaseRandomLottos(int purchaseAmount);
}

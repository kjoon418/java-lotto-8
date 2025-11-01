package lotto.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lotto.dto.LottoPurchaseDto;

public class LottoPurchaseImpl implements LottoPurchase {
    private final int price;

    public LottoPurchaseImpl(int price) {
        this.price = price;
    }

    @Override
    public LottoPurchaseDto purchaseRandomLottos(int purchaseAmount) {
        validatePurchaseAmount(purchaseAmount);

        int lottoAmount = getLottoAmount(purchaseAmount);
        List<Lotto> lottos = new ArrayList<>(lottoAmount);
        for (int i = 0; i < lottoAmount; i++) {
            lottos.add(Lotto.random());
        }

        return new LottoPurchaseDto(Collections.unmodifiableList(lottos), purchaseAmount);
    }

    private void validatePurchaseAmount(int purchaseAmount) {
        if (purchaseAmount % price != 0) {
            throw new IllegalArgumentException("구입 금액은 " + price + "원 단위여야 합니다.");
        }
    }

    private int getLottoAmount(int purchaseAmount) {
        return purchaseAmount / price;
    }
}

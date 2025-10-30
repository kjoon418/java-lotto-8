package lotto.view;

import java.util.List;
import lotto.dto.LottoDto;

public interface LottoOutputView {
    void printPurchaseAmountGuide();

    void printPurchasedLottos(List<LottoDto> lottos);

    void printEmptyLine();
}

package lotto.view;

import java.util.List;
import lotto.dto.LottoDto;

public interface LottoOutputView {
    void printPurchaseAmountGuide();

    void printWinningNumbersGuide();

    void printPurchasedLottos(List<LottoDto> lottos);

    void printEmptyLine();
}

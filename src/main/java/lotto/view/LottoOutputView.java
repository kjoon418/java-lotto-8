package lotto.view;

import java.util.List;
import lotto.dto.LottoDto;
import lotto.dto.LottoStatisticDto;

public interface LottoOutputView {
    void printPurchaseAmountGuide();

    void printWinningNumbersGuide();

    void printBonusNumberGuide();

    void printPurchasedLottos(List<LottoDto> lottos);

    void printResultStatistic(LottoStatisticDto statistic);

    void printErrorMessage(Exception error);

    void printEmptyLine();
}

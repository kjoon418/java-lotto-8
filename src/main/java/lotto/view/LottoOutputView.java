package lotto.view;

import java.util.List;
import lotto.dto.LottoDto;
import lotto.dto.LottoStatisticDto;

public interface LottoOutputView extends OutputView {
    void printPurchasedLottos(List<LottoDto> lottos);

    void printResultStatistic(LottoStatisticDto statistic);
}

package lotto.view.output;

import java.util.List;
import lotto.dto.LottoDto;
import lotto.dto.LottoStatisticDto;

public interface LottoOutputView extends OutputView {
    void printPurchasedLottos(List<LottoDto> lottos);

    void printStatistic(LottoStatisticDto statistic);
}

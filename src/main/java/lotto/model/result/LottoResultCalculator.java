package lotto.model.result;

import java.util.List;
import lotto.model.lotto.Lotto;
import lotto.model.lotto.WinningLotto;

public interface LottoResultCalculator {
    List<LottoResult> calculate(List<Lotto> lottos, WinningLotto winningLotto);

    LottoResult calculate(Lotto lotto, WinningLotto winningLotto);
}

package lotto.model;

import java.util.List;

public interface LottoResultCalculator {
    List<LottoResult> calculate(List<Lotto> lottos, WinningLotto winningLotto);

    LottoResult calculate(Lotto lotto, WinningLotto winningLotto);
}

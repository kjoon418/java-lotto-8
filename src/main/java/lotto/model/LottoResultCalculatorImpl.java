package lotto.model;

import java.util.List;

public class LottoResultCalculatorImpl implements LottoResultCalculator {
    @Override
    public List<LottoResult> calculate(List<Lotto> lottos, WinningLotto winningLotto) {
        return lottos.stream()
                .map(lotto -> calculate(lotto, winningLotto))
                .toList();
    }

    @Override
    public LottoResult calculate(Lotto lotto, WinningLotto winningLotto) {
        int equalCount = winningLotto.getEqualCount(lotto);
        boolean bonusEqual = winningLotto.bonusNumberMatchedWith(lotto);

        return LottoResult.of(equalCount, bonusEqual);
    }
}

package lotto.model;

public class LottoResultCalculatorImpl implements LottoResultCalculator {
    @Override
    public LottoResult calculate(Lotto lotto, WinningLotto winningLotto) {
        int equalCount = lotto.getEqualCount(winningLotto);
        boolean bonusEqual = winningLotto.bonusNumberMatchedWith(lotto);

        return LottoResult.of(equalCount, bonusEqual);
    }
}

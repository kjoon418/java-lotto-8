package lotto.model;

public class LottoResultCalculatorImpl implements LottoResultCalculator {
    @Override
    public LottoResult calculate(Lotto lotto, Lotto winningLotto, int bonusNumber) {
        int equalCount = lotto.getEqualCount(winningLotto);
        boolean bonusEqual = lotto.contains(bonusNumber);

        return LottoResult.of(equalCount, bonusEqual);
    }
}

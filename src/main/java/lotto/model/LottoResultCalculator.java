package lotto.model;

public interface LottoResultCalculator {
    LottoResult calculate(Lotto lotto, Lotto winningLotto, int bonusNumber);
}

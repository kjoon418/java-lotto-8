package lotto.model.lotto;

public class WinningLotto {
    private static final String DUPLICATED_NUMBER = "보너스 번호가 당첨 번호와 중복됩니다.";

    private final Lotto lotto;
    private final LottoNumber bonusNumber;

    public WinningLotto(Lotto lotto, LottoNumber bonusNumber) {
        validateDuplicate(lotto, bonusNumber);

        this.lotto = lotto;
        this.bonusNumber = bonusNumber;
    }

    public boolean bonusNumberMatchedWith(Lotto otherLotto) {
        return otherLotto.contains(bonusNumber);
    }

    public int getEqualCount(Lotto lotto) {
        return (int) lotto.numbers().stream()
                .filter(this.lotto::contains)
                .count();
    }

    private void validateDuplicate(Lotto lotto, LottoNumber bonusNumber) {
        if (lotto.contains(bonusNumber)) {
            throw new IllegalArgumentException(DUPLICATED_NUMBER);
        }
    }
}

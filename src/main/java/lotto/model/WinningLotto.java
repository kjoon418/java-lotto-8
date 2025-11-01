package lotto.model;

import java.util.List;

public class WinningLotto extends Lotto {
    private final int bonusNumber;

    public WinningLotto(List<Integer> numbers, int bonusNumber) {
        super(numbers);

        validateDuplicate(bonusNumber);
        validateRange(bonusNumber);

        this.bonusNumber = bonusNumber;
    }

    public boolean bonusNumberMatchedWith(Lotto otherLotto) {
        return otherLotto.contains(bonusNumber);
    }

    private void validateDuplicate(int bonusNumber) {
        if (contains(bonusNumber)) {
            throw new IllegalArgumentException("보너스 번호가 당첨 번호와 중복됩니다.");
        }
    }

    private void validateRange(int bonusNumber) {
        if (isOutOfRange(bonusNumber)) {
            throw new IllegalArgumentException("보너스 번호는 [" + MINIMUM + ", " + MAXIMUM + "] 범위에 속해야 합니다.");
        }
    }
}

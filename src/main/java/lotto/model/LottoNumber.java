package lotto.model;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;
import java.util.Objects;

public class LottoNumber {
    private static final int MINIMUM = 1;
    private static final int MAXIMUM = 45;

    private final int number;

    public LottoNumber(int number) {
        validateRange(number);

        this.number = number;
    }

    public static List<LottoNumber> randomNumbers(int size) {
        return Randoms.pickUniqueNumbersInRange(MINIMUM, MAXIMUM, size)
                .stream()
                .map(LottoNumber::new)
                .toList();
    }

    public int get() {
        return number;
    }

    private void validateRange(int number) {
        if (isOutOfRange(number)) {
            throw new IllegalArgumentException("로또 번호는 [" + MINIMUM + ", " + MAXIMUM + "] 범위에 속해야 합니다.");
        }
    }

    private boolean isOutOfRange(Integer number) {
        return number < MINIMUM || number > MAXIMUM;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (other == null || getClass() != other.getClass()) {
            return false;
        }

        LottoNumber otherLottoNumber = (LottoNumber) other;

        return number == otherLottoNumber.number;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(number);
    }
}

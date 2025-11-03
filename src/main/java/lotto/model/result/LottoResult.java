package lotto.model.result;

import java.util.Arrays;

public enum LottoResult {
    NO_PRIZE(
            0,
            0,
            false
    ),
    THREE_EQUAL(
        3,
            5_000,
            false
    ),
    FOUR_EQUAL(
4,
            50_000,
            false
    ),
    FIVE_EQUAL(
5,
            1_500_000,
            false
    ),
    FIVE_EQUAL_WITH_BONUS(
5,
            30_000_000,
            true
    ),
    SIX_EQUAL(
6,
            2_000_000_000,
            false
    );

    private final int equalCount;
    private final int prize;
    private final boolean bonusNumberEqual;

    LottoResult(
            int equalCount,
            int prize,
            boolean bonusNumberEqual
    ) {
        this.equalCount = equalCount;
        this.prize = prize;
        this.bonusNumberEqual = bonusNumberEqual;
    }

    public static LottoResult of(int equalCount, boolean bonusNumberEqual) {
        if (equalCount == FIVE_EQUAL_WITH_BONUS.equalCount && bonusNumberEqual) {
            return FIVE_EQUAL_WITH_BONUS;
        }

        return Arrays.stream(values())
                .filter(result -> result.equalCount == equalCount)
                .findFirst()
                .orElse(NO_PRIZE);
    }

    public boolean hasPrize() {
        return prize > 0;
    }

    public int getEqualCount() {
        return equalCount;
    }

    public int getPrize() {
        return prize;
    }

    public boolean isBonusNumberEqual() {
        return bonusNumberEqual;
    }
}

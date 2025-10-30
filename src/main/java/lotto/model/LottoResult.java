package lotto.model;

public enum LottoResult {
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

    public final int equalCount;
    public final int prize;
    public final boolean bonusNumberEqual;

    LottoResult(
            int equalCount,
            int prize,
            boolean bonusNumberEqual
    ) {
        this.equalCount = equalCount;
        this.prize = prize;
        this.bonusNumberEqual = bonusNumberEqual;
    }
}

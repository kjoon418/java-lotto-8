package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class RandomLottoPurchaseTest {
    private static final int PRICE = 1_000;

    private final RandomLottoPurchase randomLottoPurchase = new RandomLottoPurchase(PRICE);

    @ParameterizedTest
    @ValueSource(ints = {1_000, 15_000, 50_000, 100_000})
    void 구입금액만큼_로또를_발행한다(int purchaseAmount) {
        // given
        int expectedLottoAmount = purchaseAmount / PRICE;

        // when
        List<Lotto> lottos = randomLottoPurchase.purchase(purchaseAmount);

        // then
        assertThat(lottos.size()).isEqualTo(expectedLottoAmount);
    }

    @ParameterizedTest
    @ValueSource(ints = {1_500, 15_100, 50_300, 100_999})
    void 구입금액이_가격에_나누어_떨어지지_않는다면_예외를_던진다(int illegalPurchaseAmount) {
        assertThatThrownBy(() -> randomLottoPurchase.purchase(illegalPurchaseAmount))
                .isInstanceOf(IllegalArgumentException.class);
    }
}

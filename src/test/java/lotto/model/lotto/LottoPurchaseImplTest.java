package lotto.model.lotto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import lotto.dto.LottoPurchaseDto;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottoPurchaseImplTest {
    private static final int PRICE = 1_000;

    private final LottoPurchase lottoPurchase = new LottoPurchaseImpl(PRICE);

    @ParameterizedTest
    @ValueSource(ints = {1_000, 15_000, 50_000, 100_000})
    void 구입금액만큼_로또를_발행한다(int purchaseAmount) {
        // given
        int expectedLottoAmount = purchaseAmount / PRICE;

        // when
        LottoPurchaseDto result = lottoPurchase.purchaseRandomLottos(purchaseAmount);

        // then
        List<Lotto> lottos = result.lottos();
        assertThat(lottos.size()).isEqualTo(expectedLottoAmount);
        int usedPurchaseAmount = result.usedPurchaseAmount();
        assertThat(usedPurchaseAmount).isEqualTo(purchaseAmount);
    }

    @ParameterizedTest
    @ValueSource(ints = {1_500, 15_100, 50_300, 100_999})
    void 구입금액이_가격에_나누어_떨어지지_않는다면_예외를_던진다(int illegalPurchaseAmount) {
        assertThatThrownBy(() -> lottoPurchase.purchaseRandomLottos(illegalPurchaseAmount))
                .isInstanceOf(IllegalArgumentException.class);
    }
}

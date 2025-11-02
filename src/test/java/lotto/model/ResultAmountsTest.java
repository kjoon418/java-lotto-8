package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

class ResultAmountsTest {
    @Test
    void 수량은_모두_0으로_초기화한다() {
        // given
        List<LottoResult> resultTypes = everyLottoResult();

        // when
        ResultAmounts resultAmounts = new ResultAmounts(resultTypes);

        // then
        boolean allZero = resultAmounts.get()
                .values()
                .stream()
                .allMatch(amount -> amount == 0);
        assertThat(allZero).isTrue();
    }

    @Test
    void 수량을_1_증가시킨다() {
        // given
        List<LottoResult> resultTypes = everyLottoResult();
        LottoResult typeToIncrease = resultTypes.getFirst();
        ResultAmounts resultAmounts = new ResultAmounts(resultTypes);
        Integer existAmount = resultAmounts.get()
                .get(typeToIncrease);

        // when
        resultAmounts.increaseAmount(typeToIncrease);

        // then
        Integer increasedAmount = resultAmounts.get()
                .get(typeToIncrease);
        assertThat(increasedAmount).isEqualTo(existAmount + 1);
    }

    private List<LottoResult> everyLottoResult() {
        return Arrays.stream(LottoResult.values())
                .toList();
    }
}

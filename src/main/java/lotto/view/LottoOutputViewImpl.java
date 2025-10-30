package lotto.view;

import java.util.List;
import java.util.stream.Collectors;
import lotto.dto.LottoDto;

public class LottoOutputViewImpl implements LottoOutputView {
    private static final String LOTTO_PREFIX = "[";
    private static final String LOTTO_SUFFIX = "]";
    private static final String LOTTO_DELIMITER = ", ";

    @Override
    public void printPurchaseAmountGuide() {
        System.out.println("구입금액을 입력해 주세요.");
    }

    @Override
    public void printWinningNumbersGuide() {
        System.out.println("당첨 번호를 입력해 주세요.");
    }

    @Override
    public void printBonusNumberGuide() {
        System.out.println("보너스 번호를 입력해 주세요.");
    }

    @Override
    public void printPurchasedLottos(List<LottoDto> lottos) {
        System.out.printf("%d개를 구매했습니다.", lottos.size());

        for (LottoDto lotto : lottos) {
            printLotto(lotto);
        }

        printEmptyLine();
    }

    @Override
    public void printEmptyLine() {
        System.out.println();
    }

    private void printLotto(LottoDto lotto) {
        String joinedNumbers = lotto.numbers().stream()
                .map(Object::toString)
                .collect(Collectors.joining(LOTTO_DELIMITER));

        System.out.println(LOTTO_PREFIX + joinedNumbers + LOTTO_SUFFIX);
    }
}

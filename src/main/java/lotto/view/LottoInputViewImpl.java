package lotto.view;

import camp.nextstep.edu.missionutils.Console;

public class LottoInputViewImpl implements LottoInputView {
    @Override
    public String readPurchaseAmount() {
        return Console.readLine();
    }

    @Override
    public String readWinningNumbers() {
        return Console.readLine();
    }
}

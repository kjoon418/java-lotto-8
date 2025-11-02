package lotto.view.input;

public interface LottoInputView {
    String readPurchaseAmount();

    String readWinningNumbers();

    String readBonusNumber();

    void close();
}

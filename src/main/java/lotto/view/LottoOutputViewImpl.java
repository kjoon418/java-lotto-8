package lotto.view;

public class LottoOutputViewImpl implements LottoOutputView {
    @Override
    public void printPurchaseAmountGuide() {
        System.out.println("구입금액을 입력해 주세요.");
    }

    @Override
    public void printEmptyLine() {
        System.out.println();
    }
}

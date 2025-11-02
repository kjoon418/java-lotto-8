package lotto.view.output;

public class GuideOutputViewImpl implements GuideOutputView {
    private static final String PURCHASE_AMOUNT_GUIDE = "구입금액을 입력해 주세요.";
    private static final String WINNING_NUMBERS_GUIDE = "당첨 번호를 입력해 주세요.";
    private static final String BONUS_NUMBER_GUIDE = "보너스 번호를 입력해 주세요.";

    @Override
    public void printPurchaseAmountGuide() {
        System.out.println(PURCHASE_AMOUNT_GUIDE);
    }

    @Override
    public void printWinningNumbersGuide() {
        System.out.println(WINNING_NUMBERS_GUIDE);
    }

    @Override
    public void printBonusNumberGuide() {
        System.out.println(BONUS_NUMBER_GUIDE);
    }
}

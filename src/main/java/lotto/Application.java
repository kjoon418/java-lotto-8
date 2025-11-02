package lotto;

import lotto.config.LottoConfig;
import lotto.controller.LottoController;

public class Application {
    public static void main(String[] args) {
        LottoConfig config = new LottoConfig();
        LottoController controller = config.lottoController();

        controller.run();
    }
}

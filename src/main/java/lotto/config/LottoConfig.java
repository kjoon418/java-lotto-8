package lotto.config;

import lotto.controller.LottoController;
import lotto.model.InputParser;
import lotto.model.InputParserImpl;
import lotto.model.LottoAnalyzer;
import lotto.model.LottoAnalyzerImpl;
import lotto.model.LottoPurchase;
import lotto.model.LottoPurchaseImpl;
import lotto.model.LottoResultCalculator;
import lotto.model.LottoResultCalculatorImpl;
import lotto.view.ErrorOutputView;
import lotto.view.ErrorOutputViewImpl;
import lotto.view.GuideOutputView;
import lotto.view.GuideOutputViewImpl;
import lotto.view.LottoInputView;
import lotto.view.LottoInputViewImpl;
import lotto.view.LottoOutputView;
import lotto.view.LottoOutputViewImpl;

public class LottoConfig {
    private static final int LOTTO_PRICE = 1_000;

    // Views
    private final LottoInputView lottoInputView = new LottoInputViewImpl();
    private final LottoOutputView lottoOutputView = new LottoOutputViewImpl();
    private final ErrorOutputView errorOutputView = new ErrorOutputViewImpl();
    private final GuideOutputView guideOutputView = new GuideOutputViewImpl();

    // Models
    private final InputParser inputParser = new InputParserImpl();
    private final LottoPurchase lottoPurchase = new LottoPurchaseImpl(LOTTO_PRICE);
    private final LottoResultCalculator lottoResultCalculator = new LottoResultCalculatorImpl();
    private final LottoAnalyzer lottoAnalyzer = new LottoAnalyzerImpl();

    // Controller
    private final LottoController lottoController = new LottoController(
            lottoInputView,
            lottoOutputView,
            errorOutputView,
            guideOutputView,
            inputParser,
            lottoPurchase,
            lottoResultCalculator,
            lottoAnalyzer
    );

    public LottoController lottoController() {
        return lottoController;
    }
}

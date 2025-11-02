package lotto.config;

import lotto.controller.LottoController;
import lotto.model.parser.InputParser;
import lotto.model.parser.InputParserImpl;
import lotto.model.result.LottoAnalyzer;
import lotto.model.result.LottoAnalyzerImpl;
import lotto.model.lotto.LottoPurchase;
import lotto.model.lotto.LottoPurchaseImpl;
import lotto.model.result.LottoResultCalculator;
import lotto.model.result.LottoResultCalculatorImpl;
import lotto.view.output.ErrorOutputView;
import lotto.view.output.ErrorOutputViewImpl;
import lotto.view.output.GuideOutputView;
import lotto.view.output.GuideOutputViewImpl;
import lotto.view.input.LottoInputView;
import lotto.view.input.LottoInputViewImpl;
import lotto.view.output.LottoOutputView;
import lotto.view.output.LottoOutputViewImpl;

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

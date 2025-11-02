package lotto.controller;

import static lotto.util.RetryUtils.retryIfIllegalArgument;

import java.util.List;
import lotto.dto.LottoDto;
import lotto.dto.LottoPurchaseDto;
import lotto.dto.LottoStatisticDto;
import lotto.model.parser.InputParser;
import lotto.model.lotto.Lotto;
import lotto.model.result.LottoAnalyzer;
import lotto.model.lotto.LottoNumber;
import lotto.model.lotto.LottoPurchase;
import lotto.model.result.LottoResult;
import lotto.model.result.LottoResultCalculator;
import lotto.model.lotto.WinningLotto;
import lotto.view.output.ErrorOutputView;
import lotto.view.output.GuideOutputView;
import lotto.view.input.LottoInputView;
import lotto.view.output.LottoOutputView;

public class LottoController {
    private final LottoInputView inputView;
    private final LottoOutputView lottoOutputView;
    private final ErrorOutputView errorOutputView;
    private final GuideOutputView guideOutputView;
    private final InputParser inputParser;
    private final LottoPurchase purchase;
    private final LottoResultCalculator resultCalculator;
    private final LottoAnalyzer analyzer;

    public LottoController(
            LottoInputView inputView,
            LottoOutputView lottoOutputView,
            ErrorOutputView errorOutputView,
            GuideOutputView guideOutputView,
            InputParser inputParser,
            LottoPurchase purchase,
            LottoResultCalculator resultCalculator,
            LottoAnalyzer analyzer
    ) {
        this.inputView = inputView;
        this.lottoOutputView = lottoOutputView;
        this.errorOutputView = errorOutputView;
        this.guideOutputView = guideOutputView;
        this.inputParser = inputParser;
        this.purchase = purchase;
        this.resultCalculator = resultCalculator;
        this.analyzer = analyzer;
    }

    public void run() {
        LottoPurchaseDto purchaseDto = retryIfIllegalArgument(this::purchaseLottos, errorOutputView::printErrorMessage);
        lottoOutputView.printPurchasedLottos(toDtos(purchaseDto.lottos()));

        Lotto winningNumberLotto = retryIfIllegalArgument(this::getWinningNumberLotto, errorOutputView::printErrorMessage);
        WinningLotto winningLotto = retryIfIllegalArgument(() -> createWinningLotto(winningNumberLotto), errorOutputView::printErrorMessage);

        inputView.close();

        List<LottoResult> results = resultCalculator.calculate(purchaseDto.lottos(), winningLotto);
        LottoStatisticDto statistic = analyzer.analyze(results, purchaseDto.usedPurchaseAmount());
        lottoOutputView.printResultStatistic(statistic);
    }

    private LottoPurchaseDto purchaseLottos() {
        guideOutputView.printPurchaseAmountGuide();
        String rawPurchaseAmount = inputView.readPurchaseAmount();
        int purchaseAmount = inputParser.parsePurchaseAmount(rawPurchaseAmount);
        lottoOutputView.printEmptyLine();

        return purchase.purchaseRandomLottos(purchaseAmount);
    }

    private List<LottoDto> toDtos(List<Lotto> lottos) {
        return lottos.stream()
                .map(LottoDto::from)
                .toList();
    }

    private WinningLotto createWinningLotto(Lotto winningNumberLotto) {
        LottoNumber bonusNumber = getBonusNumber();

        return new WinningLotto(winningNumberLotto, bonusNumber);
    }

    private Lotto getWinningNumberLotto() {
        guideOutputView.printWinningNumbersGuide();
        String rawWinningNumbers = inputView.readWinningNumbers();
        guideOutputView.printEmptyLine();

        List<LottoNumber> winningNumbers = inputParser.parseWinningNumbers(rawWinningNumbers);

        return new Lotto(winningNumbers);
    }

    private LottoNumber getBonusNumber() {
        guideOutputView.printBonusNumberGuide();
        String rawBonusNumber = inputView.readBonusNumber();
        guideOutputView.printEmptyLine();

        return inputParser.parseBonusNumber(rawBonusNumber);
    }
}

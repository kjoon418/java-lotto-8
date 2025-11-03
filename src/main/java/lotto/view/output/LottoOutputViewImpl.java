package lotto.view.output;

import static java.lang.System.lineSeparator;
import static java.lang.System.out;

import java.text.NumberFormat;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;
import lotto.dto.LottoDto;
import lotto.dto.LottoStatisticDto;
import lotto.model.result.LottoResult;

public class LottoOutputViewImpl implements LottoOutputView {
    private static final String LOTTO_PREFIX = "[";
    private static final String LOTTO_SUFFIX = "]";
    private static final String LOTTO_DELIMITER = ", ";

    private static final String STATISTIC_HEADER = "당첨 통계" + lineSeparator() + "---";

    @Override
    public void printPurchasedLottos(List<LottoDto> lottos) {
        out.printf("%d개를 구매했습니다." + lineSeparator(), lottos.size());

        for (LottoDto lotto : lottos) {
            printLotto(lotto);
        }

        printEmptyLine();
    }

    @Override
    public void printStatistic(LottoStatisticDto statistic) {
        out.println(STATISTIC_HEADER);

        Map<LottoResult, Integer> sortedResults = getResultsSortedAscending(statistic.resultAmounts());
        for (Map.Entry<LottoResult, Integer> entry : sortedResults.entrySet()) {
            LottoResult result = entry.getKey();
            Integer lottoAmount = entry.getValue();

            printResult(result, lottoAmount);
        }

        printProfitRate(statistic.profitRate());
    }

    private void printLotto(LottoDto lotto) {
        List<Integer> sortedNumbers = sortAscending(lotto.numbers());

        String joinedNumbers = sortedNumbers.stream()
                .map(Object::toString)
                .collect(Collectors.joining(LOTTO_DELIMITER));

        out.println(LOTTO_PREFIX + joinedNumbers + LOTTO_SUFFIX);
    }

    private List<Integer> sortAscending(List<Integer> numbers) {
        return numbers.stream()
                .sorted()
                .toList();
    }

    private Map<LottoResult, Integer> getResultsSortedAscending(Map<LottoResult, Integer> results) {
        return results.entrySet()
                .stream()
                .sorted(Comparator.comparingInt(entry -> entry.getKey().getPrize()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }

    private void printResult(LottoResult result, int lottoAmount) {
        String outputFormat = getResultOutputFormat(result);

        out.printf(
                outputFormat,
                result.getEqualCount(),
                formatPrize(result.getPrize()),
                lottoAmount
        );
    }

    private String getResultOutputFormat(LottoResult lottoResult) {
        if (lottoResult.isBonusNumberEqual()) {
            return "%d개 일치, 보너스 볼 일치 (%s원) - %d개" + lineSeparator();
        }

        return "%d개 일치 (%s원) - %d개" + lineSeparator();
    }

    private void printProfitRate(double profitRate) {
        out.printf(
                "총 수익률은 %s입니다." + lineSeparator(),
                formatProfitRate(profitRate)
        );
    }

    private String formatPrize(Integer number) {
        return NumberFormat.getNumberInstance(Locale.KOREA).format(number);
    }

    private String formatProfitRate(double profitRate) {
        NumberFormat formatter = NumberFormat.getPercentInstance();
        formatter.setMinimumFractionDigits(1);
        formatter.setMaximumFractionDigits(1);

        return formatter.format(profitRate);
    }
}

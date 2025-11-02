package lotto.model.parser;

import java.util.List;
import lotto.model.lotto.LottoNumber;

public interface InputParser {
    int parsePurchaseAmount(String rawInput);

    List<LottoNumber> parseWinningNumbers(String rawInput);

    LottoNumber parseBonusNumber(String rawInput);
}

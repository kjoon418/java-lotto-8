package lotto.model;

import java.util.List;

public interface InputParser {
    int parsePurchaseAmount(String rawInput);

    List<Integer> parseWinningNumbers(String rawInput);

    int parseBonusNumber(String rawInput);
}

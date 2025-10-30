package lotto.model;

import java.util.List;

public interface InputParser {
    List<Integer> parseWinningNumbers(String rawInput);
}

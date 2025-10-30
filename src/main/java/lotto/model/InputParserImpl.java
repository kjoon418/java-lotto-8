package lotto.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.platform.commons.util.StringUtils;

public class InputParserImpl implements InputParser {
    private static final String DELIMITER = ",";
    private static final String NUMERIC_REGEX = "\\d+";

    @Override
    public List<Integer> parseWinningNumbers(String rawInput) {
        validateEmpty(rawInput);

        String[] splitInput = rawInput.split(DELIMITER);
        List<Integer> numbers = parseToNumbers(splitInput);

        return Collections.unmodifiableList(numbers);
    }

    private void validateEmpty(String rawInput) {
        if (StringUtils.isBlank(rawInput)) {
            throw new IllegalArgumentException("입력이 비어 있습니다.");
        }
    }

    private List<Integer> parseToNumbers(String[] splitInput) {
        List<Integer> numbers = new ArrayList<>();

        for (String input : splitInput) {
            validateNumeric(input);
            numbers.add(Integer.parseInt(input));
        }

        return numbers;
    }

    private void validateNumeric(String string) {
        if (!string.matches(NUMERIC_REGEX)) {
            throw new IllegalArgumentException("숫자가 아닌 값이 포함되어 있습니다.");
        }
    }
}

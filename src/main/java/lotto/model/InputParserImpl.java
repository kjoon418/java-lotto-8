package lotto.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.platform.commons.util.StringUtils;

public class InputParserImpl implements InputParser {
    private static final String DELIMITER = ",";
    private static final String DIGIT_REGEX = "\\d+";

    @Override
    public int parsePurchaseAmount(String rawInput) {
        validateDigit(rawInput);

        return Integer.parseInt(rawInput);
    }

    @Override
    public List<LottoNumber> parseWinningNumbers(String rawInput) {
        validateEmpty(rawInput);

        String[] splitInput = rawInput.split(DELIMITER);
        List<LottoNumber> numbers = parseToLottoNumbers(splitInput);

        return Collections.unmodifiableList(numbers);
    }

    @Override
    public LottoNumber parseBonusNumber(String rawInput) {
        validateDigit(rawInput);

        int number = Integer.parseInt(rawInput);

        return new LottoNumber(number);
    }

    private void validateEmpty(String rawInput) {
        if (StringUtils.isBlank(rawInput)) {
            throw new IllegalArgumentException("입력이 비어 있습니다.");
        }
    }

    private List<LottoNumber> parseToLottoNumbers(String[] splitInput) {
        List<LottoNumber> numbers = new ArrayList<>();

        for (String input : splitInput) {
            validateDigit(input);
            int number = Integer.parseInt(input);
            numbers.add(new LottoNumber(number));
        }

        return numbers;
    }

    private void validateDigit(String string) {
        if (string == null || !string.matches(DIGIT_REGEX)) {
            throw new IllegalArgumentException("값이 정수 형태가 아닙니다.");
        }
    }
}

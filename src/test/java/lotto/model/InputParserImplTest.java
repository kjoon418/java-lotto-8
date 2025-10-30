package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

class InputParserImplTest {
    private static final String INPUT_DELIMITER = ",";

    private final InputParser inputParser = new InputParserImpl();

    @Test
    void 입력을_숫자_컬렉션으로_변환한다() {
        // given
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
        String input = joinNumbers(numbers);

        // when
        List<Integer> parsedNumbers = inputParser.parseWinningNumbers(input);

        // then
        assertThat(parsedNumbers).containsExactlyElementsOf(numbers);
    }

    @ParameterizedTest
    @NullAndEmptySource
    void 입력이_비어_있거나_null이라면_예외를_던진다(String emptyInput) {
        assertThatThrownBy(() -> inputParser.parseWinningNumbers(emptyInput))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 숫자가_아닌_입력이_있다면_예외를_던진다() {
        // given
        List<String> illegalNumbers = List.of("1", "2", "a", "4", "5");
        String illegalInput = joinNumbers(illegalNumbers);

        // when & then
        assertThatThrownBy(() -> inputParser.parseWinningNumbers(illegalInput))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private <T> String joinNumbers(List<T> numbers) {
        return numbers.stream()
                .map(Object::toString)
                .collect(Collectors.joining(InputParserImplTest.INPUT_DELIMITER));
    }
}

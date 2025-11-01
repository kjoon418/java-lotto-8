package lotto.util;

import java.util.Arrays;
import java.util.List;
import lotto.model.LottoNumber;

public class TestUtils {
    private TestUtils() {
    }

    public static List<LottoNumber> toLottoNumbers(List<Integer> numbers) {
        return numbers.stream()
                .map(LottoNumber::new)
                .toList();
    }

    public static List<LottoNumber> lottoNumbersOf(int... numbers) {
        return Arrays.stream(numbers)
                .mapToObj(LottoNumber::new)
                .toList();
    }
}

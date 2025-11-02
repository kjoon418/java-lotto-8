package lotto.dto;

import java.util.List;
import lotto.model.Lotto;
import lotto.model.LottoNumber;

public record LottoDto(
        List<Integer> numbers
) {
    public static LottoDto from(Lotto lotto) {
        List<Integer> numbers = lotto.numbers().stream()
                .map(LottoNumber::get)
                .toList();

        return new LottoDto(numbers);
    }
}

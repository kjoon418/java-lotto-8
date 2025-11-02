package lotto.dto;

import java.util.List;
import lotto.model.lotto.Lotto;
import lotto.model.lotto.LottoNumber;

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

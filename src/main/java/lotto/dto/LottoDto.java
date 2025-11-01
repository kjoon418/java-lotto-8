package lotto.dto;

import java.util.List;
import lotto.model.Lotto;

public record LottoDto(
        List<Integer> numbers
) {
    public LottoDto from(Lotto lotto) {
        List<Integer> numbers = lotto.getNumbers();

        return new LottoDto(numbers);
    }
}

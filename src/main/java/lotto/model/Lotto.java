package lotto.model;

import java.util.List;

public record Lotto(
        List<LottoNumber> numbers
) {
    private static final int SIZE = 6;

    public Lotto {
        validateSize(numbers);
        validateDuplicate(numbers);
    }

    public static Lotto random() {
        List<LottoNumber> lottoNumbers = LottoNumber.randomNumbers(SIZE);

        return new Lotto(lottoNumbers);
    }

    @Override
    public List<LottoNumber> numbers() {
        return List.copyOf(numbers);
    }

    public boolean contains(LottoNumber number) {
        return numbers.contains(number);
    }

    private void validateSize(List<LottoNumber> numbers) {
        if (numbers.size() != SIZE) {
            throw new IllegalArgumentException("로또 번호는 " + SIZE + "개여야 합니다.");
        }
    }

    private void validateDuplicate(List<LottoNumber> numbers) {
        long distinctSize = numbers.stream()
                .distinct()
                .count();

        if (numbers.size() != distinctSize) {
            throw new IllegalArgumentException("로또 번호는 중복될 수 업습니다.");
        }
    }
}

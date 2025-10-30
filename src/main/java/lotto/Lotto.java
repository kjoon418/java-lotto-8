package lotto;

import java.util.List;

public class Lotto {
    private static final int SIZE = 6;
    private static final int MINIMUM = 1;
    private static final int MAXIMUM = 45;

    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validateSize(numbers);
        validateRange(numbers);
        validateDuplicate(numbers);

        this.numbers = numbers;
    }

    private void validateSize(List<Integer> numbers) {
        if (numbers.size() != SIZE) {
            throw new IllegalArgumentException("로또 번호는 " + SIZE + "개여야 합니다.");
        }
    }

    private void validateRange(List<Integer> numbers) {
        boolean outOfRange = numbers.stream()
                .anyMatch(this::isOutOfRange);

        if (outOfRange) {
            throw new IllegalArgumentException("로또 번호는 [" + MINIMUM + ", " + MAXIMUM + "] 범위에 속해야 합니다.");
        }
    }

    private boolean isOutOfRange(Integer number) {
        return number < MINIMUM || number > MAXIMUM;
    }

    private void validateDuplicate(List<Integer> numbers) {
        long distinctSize = numbers.stream()
                .distinct()
                .count();

        if (numbers.size() != distinctSize) {
            throw new IllegalArgumentException("로또 번호는 중복될 수 업습니다.");
        }
    }
}

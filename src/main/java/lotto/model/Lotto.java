package lotto.model;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;

public class Lotto {
    private static final int SIZE = 6;
    protected static final int MINIMUM = 1;
    protected static final int MAXIMUM = 45;

    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validateSize(numbers);
        validateRange(numbers);
        validateDuplicate(numbers);

        this.numbers = numbers;
    }

    public static Lotto random() {
        List<Integer> randomNumbers = Randoms.pickUniqueNumbersInRange(MINIMUM, MAXIMUM,
                SIZE);

        return new Lotto(randomNumbers);
    }

    public List<Integer> getNumbers() {
        return List.copyOf(numbers);
    }

    public int getEqualCount(Lotto otherLotto) {
        return (int) otherLotto.numbers.stream()
                .filter(this::contains)
                .count();
    }

    public boolean contains(int number) {
        return numbers.contains(number);
    }

    protected boolean isOutOfRange(Integer number) {
        return number < MINIMUM || number > MAXIMUM;
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

    private void validateDuplicate(List<Integer> numbers) {
        long distinctSize = numbers.stream()
                .distinct()
                .count();

        if (numbers.size() != distinctSize) {
            throw new IllegalArgumentException("로또 번호는 중복될 수 업습니다.");
        }
    }
}

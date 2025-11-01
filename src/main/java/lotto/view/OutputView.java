package lotto.view;

public interface OutputView {
    default void printEmptyLine() {
        System.out.println();
    }
}

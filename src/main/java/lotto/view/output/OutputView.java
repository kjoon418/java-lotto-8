package lotto.view.output;

public interface OutputView {
    default void printEmptyLine() {
        System.out.println();
    }
}

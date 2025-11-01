package lotto.view;

public class ErrorOutputViewImpl implements ErrorOutputView {
    private static final String ERROR_MESSAGE_PREFIX = "[ERROR] ";

    @Override
    public void printErrorMessage(Exception error) {
        System.out.println(ERROR_MESSAGE_PREFIX + error.getMessage());
    }
}

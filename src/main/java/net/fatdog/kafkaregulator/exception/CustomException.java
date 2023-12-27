package net.fatdog.kafkaregulator.exception;

public class CustomException extends Exception {
    private final int errorCode;
    private final String errorMessage;

    public CustomException(int errorCode) {
        super(ErrorMessageResolver.getErrorMessage(errorCode));
        this.errorCode = errorCode;
        this.errorMessage = ErrorMessageResolver.getErrorMessage(errorCode);
    }

    public CustomException(int errorCode, Throwable cause) {
        super(ErrorMessageResolver.getErrorMessage(errorCode), cause);
        this.errorCode = errorCode;
        this.errorMessage = ErrorMessageResolver.getErrorMessage(errorCode);
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}

package exceptions;

public class InvalidTimePeriodException extends Exception {
    public InvalidTimePeriodException() {
        super();
    }

    public InvalidTimePeriodException(String errorMessage) {
        super(errorMessage);
    }

    public InvalidTimePeriodException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}

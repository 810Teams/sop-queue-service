package exceptions;

public class InvalidStatusFlowException extends Exception {
    public InvalidStatusFlowException() {
        super();
    }

    public InvalidStatusFlowException(String errorMessage) {
        super(errorMessage);
    }

    public InvalidStatusFlowException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}

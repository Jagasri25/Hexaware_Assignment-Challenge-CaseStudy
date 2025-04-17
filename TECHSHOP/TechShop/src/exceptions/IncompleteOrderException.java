package exceptions;

public class IncompleteOrderException extends Exception {
    public IncompleteOrderException(String incompleteOrderExceptionMsg) {
        super(incompleteOrderExceptionMsg);
    }
}
package exceptions;

public class ConcurrencyException extends Exception {
    public ConcurrencyException(String concurrencyExceptionMsg) {
        super(concurrencyExceptionMsg);
    }
}
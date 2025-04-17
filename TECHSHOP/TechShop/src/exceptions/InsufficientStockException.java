package exceptions;

public class InsufficientStockException extends Exception {
    public InsufficientStockException(String insufficientStockExceptionMsg) {
        super(insufficientStockExceptionMsg);
    }
}
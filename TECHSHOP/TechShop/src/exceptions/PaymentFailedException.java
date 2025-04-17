package exceptions;

public class PaymentFailedException extends Exception {
    public PaymentFailedException(String paymentFailedExceptionMsg) {
        super(paymentFailedExceptionMsg);
    }
}
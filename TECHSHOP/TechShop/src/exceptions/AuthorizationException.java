package exceptions;

public class AuthorizationException extends Exception {
    public AuthorizationException(String authorizationExceptionMsg) {
        super(authorizationExceptionMsg);
    }
}
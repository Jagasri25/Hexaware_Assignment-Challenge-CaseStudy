package exceptions;

public class AuthenticationException extends Exception {
    public AuthenticationException(String authenticationExceptionMsg) {
        super(authenticationExceptionMsg);
    }
}
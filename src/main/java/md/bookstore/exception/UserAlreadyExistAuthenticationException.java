package md.bookstore.exception;

import javax.naming.AuthenticationException;

public final class UserAlreadyExistAuthenticationException extends AuthenticationException {
    public UserAlreadyExistAuthenticationException(final String msg) {
        super(msg);
    }

    public UserAlreadyExistAuthenticationException() {}
}

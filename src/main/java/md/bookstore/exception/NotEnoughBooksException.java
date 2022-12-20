package md.bookstore.exception;

public final class NotEnoughBooksException extends RuntimeException {
    public NotEnoughBooksException(String message) {
        super(message);
    }
}

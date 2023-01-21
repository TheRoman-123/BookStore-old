package md.bookstore.exception;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class EmptyFieldException extends RuntimeException {
    private final String objectName;
    private final String fieldName;
}

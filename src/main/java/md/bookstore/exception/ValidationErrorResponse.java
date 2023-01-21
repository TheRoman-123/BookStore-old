package md.bookstore.exception;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public final class ValidationErrorResponse {
    private final List<Violation> violations;
}

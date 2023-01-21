package md.bookstore.exception;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ValidationErrorResponse {
    private final List<Violation> violations;
}

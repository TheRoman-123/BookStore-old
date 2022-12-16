package md.bookstore.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class RestExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(RestExceptionHandler.class.getName());

    @ExceptionHandler(OffsetOrLimitException.class)
    public ResponseEntity<Object> handleException(OffsetOrLimitException exception) {
        if (exception.getLimit() == null || exception.getOffset() == null) {
            LOGGER.warn("Request GET doesn't have offset or limit!");
        } else {
            LOGGER.warn("Request has illegal arguments: " +
                    "offset = " + exception.getOffset() + " < 0 or " +
                    "limit = " + exception.getLimit() + " <= 0.");
        }

        return new ResponseEntity<>("Check your offset and limit!", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserAlreadyExistAuthenticationException.class)
    public ResponseEntity<Object> handleException(UserAlreadyExistAuthenticationException exception) {
        String message = (exception.getMessage() == null) ?
                "User with such username already exist!" :
                exception.getMessage();
        LOGGER.warn(message);
        return new ResponseEntity<>(message, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Object> handleException(NoSuchElementException exception) {
        LOGGER.warn(exception.getMessage());
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleException(IllegalArgumentException exception) {
        LOGGER.warn(exception.getMessage());
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
//        Может, потом найдёшь более подходящий статус
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception exception) {
        LOGGER.warn(exception.getMessage());
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
}

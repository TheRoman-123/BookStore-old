package md.bookstore.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;

@RestControllerAdvice
public class RestExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(RestExceptionHandler.class.getName());

    @ExceptionHandler(IllegalPageException.class)
    public ResponseEntity<Object> handleException(IllegalPageException exception) {
        if (exception.getPageSize() == null || exception.getPageNumber() == null) {
            LOGGER.warn("Request GET doesn't have page number or page size!");
        } else {
            LOGGER.warn("Request has illegal arguments: " +
                    "page number = " + exception.getPageNumber() + " < 0 or " +
                    "page size = " + exception.getPageSize() + " <= 0.");
        }

        return new ResponseEntity<>("Check your offset and limit!", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserAlreadyExistAuthenticationException.class)
    public ResponseEntity<Object> handleException(UserAlreadyExistAuthenticationException exception) {
        String message = (exception.getMessage() == null) ?
                "User with such username already exists!" :
                exception.getMessage();
        LOGGER.warn(message);
        return new ResponseEntity<>(message, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleException(EntityNotFoundException exception) {
        LOGGER.warn(exception.getMessage());
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotEnoughBooksException.class)
    public ResponseEntity<Object> handleException(NotEnoughBooksException exception) {
        LOGGER.warn(exception.getMessage());
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<Object> handleException(IOException exception) {
        LOGGER.warn(exception.getMessage());
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleException(IllegalArgumentException exception) {
        LOGGER.error(exception.getMessage());
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
//        Может, потом найдёшь более подходящий статус
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception exception) {
        LOGGER.warn(exception.getMessage());
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
}

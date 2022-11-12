package md.bookstore.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestExceptionHandler.class.getName());


    @ExceptionHandler(MyException.class)
    public ResponseEntity<Object> handleException(MyException exception) {
        LOGGER.warn(exception.getMessage());
        return null;
    }

}

package id.co.indivara.jdt12.library.Exception;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(ResponseEntityErrorException.class)
    public ResponseEntity handleException(
            ResponseEntityErrorException e) {

        Error error = new Error(e.getStatusCode(), e.getMessage());
        return new ResponseEntity<>(error, error.getHttpStatus());
    }
}

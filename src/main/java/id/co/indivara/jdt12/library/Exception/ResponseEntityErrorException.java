package id.co.indivara.jdt12.library.Exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ResponseEntityErrorException extends RuntimeException {
    private String message;
    private HttpStatus statusCode;

    public ResponseEntityErrorException(String message, HttpStatus statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }
}

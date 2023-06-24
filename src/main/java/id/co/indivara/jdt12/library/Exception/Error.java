package id.co.indivara.jdt12.library.Exception;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@RequiredArgsConstructor
public class Error {
    private final HttpStatus httpStatus;
    private final String message;
}
package id.co.indivara.jdt12.library.handler;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@Builder
public class Response {

    private HttpStatus statusCode;
    private Object result;
}

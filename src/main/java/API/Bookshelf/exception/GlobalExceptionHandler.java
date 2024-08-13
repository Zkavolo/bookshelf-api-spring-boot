package API.Bookshelf.exception;

import API.Bookshelf.util.error.ErrorsDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<?> InvalidRequestException(InvalidRequestException ex){
        String message = ex.getMessage();
        ErrorsDTO error = ErrorsDTO.builder()
                .message(message)
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}

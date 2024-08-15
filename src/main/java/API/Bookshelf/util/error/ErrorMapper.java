package API.Bookshelf.util.error;

import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

public class ErrorMapper {
    public static ErrorResponse<?> renderErrors(Errors errors){
        ErrorResponse<?> response = new ErrorResponse<>();

        for (ObjectError error : errors.getAllErrors()){
            response.getErrors().add(error.getDefaultMessage());
        }

        return response;
    }
}

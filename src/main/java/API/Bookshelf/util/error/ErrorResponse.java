package API.Bookshelf.util.error;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ErrorResponse {
    private List<String> errors = new ArrayList<>();
    private String message;
}

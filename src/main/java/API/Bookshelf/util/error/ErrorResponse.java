package API.Bookshelf.util.error;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ErrorResponse<T> {
    private List<String> errors = new ArrayList<>();
}

package API.Bookshelf.util.DTO.category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class CategoryDeleteResponseDTO {
    private Integer id;
    private String message;
}

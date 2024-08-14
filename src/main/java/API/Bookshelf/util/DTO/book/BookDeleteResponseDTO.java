package API.Bookshelf.util.DTO.book;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookDeleteResponseDTO {
    private Integer id;
    private String message;
}

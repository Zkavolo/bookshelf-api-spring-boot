package API.Bookshelf.util.DTO.book;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookRequestDTO {
    private Integer categoryId;
    private String name;
    private Integer pages;
    private String author;
    private String publisher;
}

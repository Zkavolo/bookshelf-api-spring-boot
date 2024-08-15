package API.Bookshelf.util.DTO.book;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookRequestDTO {
    @NotNull(message = "Category id can't be blank!")
    private Integer categoryId;
    @NotBlank(message = "Book name can't be blank!")
    private String name;
    @NotNull(message = "Pages can't be blank!")
    private Integer pages;
    @NotBlank(message = "Author can't be blank!")
    private String author;
    @NotBlank(message = "Publisher can't be blank!")
    private String publisher;
}

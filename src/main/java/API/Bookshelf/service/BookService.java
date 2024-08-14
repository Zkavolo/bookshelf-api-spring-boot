package API.Bookshelf.service;

import API.Bookshelf.model.Category;
import API.Bookshelf.util.DTO.book.BookDeleteResponseDTO;
import API.Bookshelf.util.DTO.book.BookRequestDTO;
import API.Bookshelf.util.DTO.book.BookResponseDTO;
import API.Bookshelf.util.DTO.category.CategoryDeleteResponseDTO;
import API.Bookshelf.util.DTO.category.CategoryRequestDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookService {
    BookResponseDTO create(BookRequestDTO request);
    Page<BookResponseDTO> getAll(Pageable pageable, String category);
    BookResponseDTO getOne(Integer id);
    BookResponseDTO update(BookRequestDTO request, Integer id);
    BookDeleteResponseDTO delete(Integer id);
}

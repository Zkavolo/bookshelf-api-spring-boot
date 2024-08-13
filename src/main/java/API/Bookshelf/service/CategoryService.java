package API.Bookshelf.service;

import API.Bookshelf.model.Category;
import API.Bookshelf.util.DTO.category.CategoryDeleteResponseDTO;
import API.Bookshelf.util.DTO.category.CategoryRequestDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryService {
    Category create(CategoryRequestDTO request);
    Page<Category> getAll(Pageable pageable);
    Category getOne(Integer id);
    Category update(CategoryRequestDTO request, Integer id);
    CategoryDeleteResponseDTO delete(Integer id);
}

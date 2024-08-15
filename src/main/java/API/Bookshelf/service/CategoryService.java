package API.Bookshelf.service;

import API.Bookshelf.model.Category;
import API.Bookshelf.util.DTO.category.CategoryDeleteResponseDTO;
import API.Bookshelf.util.DTO.category.CategoryRequestDTO;
import API.Bookshelf.util.DTO.category.CategoryResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryService {
    CategoryResponseDTO create(CategoryRequestDTO request);
    Page<CategoryResponseDTO> getAll(Pageable pageable);
    CategoryResponseDTO getOne(Integer id);
    CategoryResponseDTO update(CategoryRequestDTO request, Integer id);
    CategoryDeleteResponseDTO delete(Integer id);
    Category getCategory(Integer id);
}

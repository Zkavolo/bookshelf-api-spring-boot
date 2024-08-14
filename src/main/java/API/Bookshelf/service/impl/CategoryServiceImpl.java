package API.Bookshelf.service.impl;

import API.Bookshelf.exception.InvalidRequestException;
import API.Bookshelf.model.Category;
import API.Bookshelf.repository.CategoryRepository;
import API.Bookshelf.service.CategoryService;
import API.Bookshelf.util.DTO.category.CategoryDeleteResponseDTO;
import API.Bookshelf.util.DTO.category.CategoryRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public Category create(CategoryRequestDTO request) {
        if(!categoryRepository.findByName(request.getName()).isEmpty()){
            throw new InvalidRequestException("You cannot add a duplicate category!");
        }

        Category category = Category.builder()
                .categoryName(request.getName())
                .createdAt(new Date())
                .updatedAt(new Date())
                .build();

        categoryRepository.save(category);

        return category;
    }

    @Override
    public Page<Category> getAll(Pageable pageable) {
        Page<Category> categoryPaged = categoryRepository.findAll(pageable);
        List<Category> categoryList = categoryPaged.stream()
                .map(category -> new Category(category.getId(), category.getCategoryName(), category.getCreatedAt(), category.getUpdatedAt()))
                .collect(Collectors.toList());

        return new PageImpl<>(categoryList, pageable, categoryPaged.getNumberOfElements());
    }

    @Override
    public Category getOne(Integer id) {
        return categoryRepository.findById(id).orElseThrow(()-> new InvalidRequestException("Category isn't found!"));
    }

    @Override
    public Category update(CategoryRequestDTO request, Integer id) {
        Category category = getOne(id);

        category.setCategoryName(request.getName());
        category.setUpdatedAt(new Date());

        categoryRepository.save(category);

        return category;
    }

    @Override
    public CategoryDeleteResponseDTO delete(Integer id) {
        Category category = getOne(id);

        CategoryDeleteResponseDTO response = CategoryDeleteResponseDTO.builder()
                .id(category.getId())
                .message(category.getCategoryName()+" has been succesfully deleted!")
                .build();

        categoryRepository.delete(category);

        return response;
    }
}

package API.Bookshelf.service.impl;

import API.Bookshelf.exception.InvalidRequestException;
import API.Bookshelf.model.Category;
import API.Bookshelf.repository.CategoryRepository;
import API.Bookshelf.service.CategoryService;
import API.Bookshelf.util.DTO.category.CategoryDeleteResponseDTO;
import API.Bookshelf.util.DTO.category.CategoryRequestDTO;
import API.Bookshelf.util.DTO.category.CategoryResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public CategoryResponseDTO create(CategoryRequestDTO request) {
        if(!categoryRepository.findByName(request.getName()).isEmpty()){
            throw new InvalidRequestException("You cannot add a duplicate category!");
        }

        Category category = Category.builder()
                .categoryName(request.getName())
                .createdAt(new Date(System.currentTimeMillis()))
                .updatedAt(new Date(System.currentTimeMillis()))
                .build();

        categoryRepository.insertTo(category);

        CategoryResponseDTO categoryResponseDTO = CategoryResponseDTO.builder()
                .categoryName(category.getCategoryName())
                .createdAt(category.getCreatedAt())
                .updatedAt(category.getUpdatedAt())
                .build();

        return categoryResponseDTO;
    }

    @Override
    public Page<CategoryResponseDTO> getAll(Pageable pageable) {
        Page<Category> categoryPaged = categoryRepository.findAll(pageable);
        List<CategoryResponseDTO> categoryList = categoryPaged.stream()
                .map(category -> new CategoryResponseDTO(category.getCategoryName(), category.getCreatedAt(), category.getUpdatedAt()))
                .collect(Collectors.toList());

        return new PageImpl<>(categoryList, pageable, categoryPaged.getNumberOfElements());
    }

    @Override
    public CategoryResponseDTO getOne(Integer id) {
        Category category = categoryRepository.getOneById(id).orElseThrow(()-> new InvalidRequestException("Category isn't found!"));

        CategoryResponseDTO categoryResponseDTO = CategoryResponseDTO.builder()
                .categoryName(category.getCategoryName())
                .createdAt(category.getCreatedAt())
                .updatedAt(category.getUpdatedAt())
                .build();

        return categoryResponseDTO;
    }

    @Override
    public CategoryResponseDTO update(CategoryRequestDTO request, Integer id) {
        Category category = categoryRepository.getOneById(id).orElseThrow(()-> new InvalidRequestException("Category isn't found!"));

        category.setCategoryName(request.getName());
        category.setUpdatedAt(new Date(System.currentTimeMillis()));

        categoryRepository.save(category);

        CategoryResponseDTO categoryResponseDTO = CategoryResponseDTO.builder()
                .categoryName(category.getCategoryName())
                .createdAt(category.getCreatedAt())
                .updatedAt(category.getUpdatedAt())
                .build();

        return categoryResponseDTO;
    }

    @Override
    public CategoryDeleteResponseDTO delete(Integer id) {
        Category category = categoryRepository.getOneById(id).orElseThrow(()-> new InvalidRequestException("Category isn't found!"));

        CategoryDeleteResponseDTO response = CategoryDeleteResponseDTO.builder()
                .id(category.getId())
                .message(category.getCategoryName()+" has been succesfully deleted!")
                .build();

        categoryRepository.deleteOneById(category.getId());

        return response;
    }

    @Override
    public Category getCategory(Integer id) {
        return categoryRepository.getOneById(id).orElseThrow(()-> new InvalidRequestException("Category isn't found!"));
    }
}

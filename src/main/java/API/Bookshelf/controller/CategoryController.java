package API.Bookshelf.controller;

import API.Bookshelf.Pageification.PageResponseWrapper;
import API.Bookshelf.model.Category;
import API.Bookshelf.service.CategoryService;
import API.Bookshelf.util.DTO.category.CategoryRequestDTO;
import API.Bookshelf.util.DTO.category.CategoryResponseDTO;
import API.Bookshelf.util.error.ErrorMapper;
import API.Bookshelf.util.error.ErrorResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.apache.coyote.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @Validated
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody CategoryRequestDTO request, Errors errors){
        if(errors.hasErrors()){
            ErrorResponse<?> response = ErrorMapper.renderErrors(errors);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.create(request));
    }

    @GetMapping
    public ResponseEntity<?> getAll(
            @PageableDefault Pageable pageable
    ){
        Page<CategoryResponseDTO> result = categoryService.getAll(pageable);
        PageResponseWrapper<CategoryResponseDTO> response = new PageResponseWrapper<>(result);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.getOne(id));
    }

    @PutMapping("/{id}")
    @Validated
    public ResponseEntity<?> update(@Valid @RequestBody CategoryRequestDTO request, @PathVariable Integer id, Errors errors){
        if(errors.hasErrors()){
            ErrorResponse<?> response = ErrorMapper.renderErrors(errors);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.update(request,id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.delete(id));
    }

}

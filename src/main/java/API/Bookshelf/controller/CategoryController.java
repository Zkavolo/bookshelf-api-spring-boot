package API.Bookshelf.controller;

import API.Bookshelf.Pageification.PageResponseWrapper;
import API.Bookshelf.model.Category;
import API.Bookshelf.service.CategoryService;
import API.Bookshelf.util.DTO.category.CategoryRequestDTO;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CategoryRequestDTO request){
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.create(request));
    }

    @GetMapping
    public ResponseEntity<?> getAll(
            Pageable pageable
    ){
        Page<Category> result = categoryService.getAll(pageable);
        PageResponseWrapper<Category> response = new PageResponseWrapper<>(result);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.getOne(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody CategoryRequestDTO request, @PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.update(request,id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.delete(id));
    }

}

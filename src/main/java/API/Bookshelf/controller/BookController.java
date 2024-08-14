package API.Bookshelf.controller;

import API.Bookshelf.Pageification.PageResponseWrapper;
import API.Bookshelf.model.Book;
import API.Bookshelf.model.Category;
import API.Bookshelf.service.BookService;
import API.Bookshelf.util.DTO.book.BookRequestDTO;
import API.Bookshelf.util.DTO.book.BookResponseDTO;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody BookRequestDTO request){
        return ResponseEntity.status(HttpStatus.OK).body(bookService.create(request));
    }

    @GetMapping
    public ResponseEntity<?> getAll(
            Pageable pageable,
            @RequestParam(required = false)String category
    ){
        Page<BookResponseDTO> result = bookService.getAll(pageable, category);
        PageResponseWrapper<BookResponseDTO> response = new PageResponseWrapper<>(result);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody BookRequestDTO request, @PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(bookService.update(request,id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(bookService.getOne(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(bookService.delete(id));
    }
}

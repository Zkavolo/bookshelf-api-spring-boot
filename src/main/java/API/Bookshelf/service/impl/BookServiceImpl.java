package API.Bookshelf.service.impl;

import API.Bookshelf.exception.InvalidRequestException;
import API.Bookshelf.model.Book;
import API.Bookshelf.model.Category;
import API.Bookshelf.repository.BookRepository;
import API.Bookshelf.repository.CategoryRepository;
import API.Bookshelf.service.BookService;
import API.Bookshelf.service.CategoryService;
import API.Bookshelf.util.DTO.book.BookDeleteResponseDTO;
import API.Bookshelf.util.DTO.book.BookRequestDTO;
import API.Bookshelf.util.DTO.book.BookResponseDTO;
import API.Bookshelf.util.DTO.category.CategoryRequestDTO;
import API.Bookshelf.util.Specification.BookSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final CategoryService categoryService;
    private final CategoryRepository categoryRepository;

    @Override
    public BookResponseDTO create(BookRequestDTO request) {
        if(!bookRepository.findByName(request.getName()).isEmpty()){
            throw new InvalidRequestException("You cannot add a duplicate book!");
        }

        Category category = categoryService.getCategory(request.getCategoryId());

        Book book = Book.builder()
                .name(request.getName())
                .category(category)
                .author(request.getAuthor())
                .publisher(request.getPublisher())
                .pages(request.getPages())
                .createdAt(new Date())
                .updatedAt(new Date())
                .build();

        bookRepository.save(book);

        BookResponseDTO response = BookResponseDTO.builder()
                .name(book.getName())
                .author(book.getAuthor())
                .publisher(book.getPublisher())
                .category(book.getCategory().getCategoryName())
                .pages(book.getPages())
                .build();

        return response;
    }

    @Override
    public Page<BookResponseDTO> getAll(Pageable pageable, String category) {
        Category categoryObj = null;
        if(category != null) categoryObj = categoryRepository.findByName(category).orElseThrow(() ->
                    new InvalidRequestException("Category isn't found!"));

        Specification<Book> spec = BookSpecification.getSpecification(categoryObj);

        Page<Book> bookPaged = bookRepository.findAll(spec, pageable);
//        Page<Book> bookPage = bookRepository.getAll(spec, pageable);
        List<BookResponseDTO> bookList = bookPaged.stream()
                .map(book -> new BookResponseDTO(book.getName(), book.getPages(),
                        book.getCategory().getCategoryName(),
                        book.getAuthor(), book.getPublisher()))
                .collect(Collectors.toList());

        return new PageImpl<>(bookList, pageable, bookPaged.getNumberOfElements());
    }

    @Override
    public BookResponseDTO getOne(Integer id) {
        Book book = bookRepository.getOneById(id).orElseThrow(()-> new InvalidRequestException("Book isn't found!"));

        BookResponseDTO response = BookResponseDTO.builder()
                .name(book.getName())
                .author(book.getAuthor())
                .publisher(book.getPublisher())
                .category(book.getCategory().getCategoryName())
                .pages(book.getPages())
                .build();

        return response;
    }

    @Override
    public BookResponseDTO update(BookRequestDTO request, Integer id) {
        Category category = categoryService.getCategory(request.getCategoryId());
        Book book = bookRepository.getOneById(id).orElseThrow(()-> new InvalidRequestException("Book isn't found!"));

        book.setAuthor(request.getAuthor());
        book.setName(request.getName());
        book.setPages(request.getPages());
        book.setCategory(category);
        book.setPublisher(request.getPublisher());
        book.setUpdatedAt(new Date());

        bookRepository.save(book);

        BookResponseDTO response = BookResponseDTO.builder()
                .name(book.getName())
                .author(book.getAuthor())
                .publisher(book.getPublisher())
                .category(book.getCategory().getCategoryName())
                .pages(book.getPages())
                .build();

        return response;
    }

    @Override
    public BookDeleteResponseDTO delete(Integer id) {
        Book book = bookRepository.getOneById(id).orElseThrow(()-> new InvalidRequestException("Book isn't found!"));

        BookDeleteResponseDTO response = BookDeleteResponseDTO.builder()
                .id(book.getId())
                .message(book.getName()+" has been succesfully deleted!")
                .build();

        bookRepository.deleteOneById(book.getId());

        return response;
    }
}

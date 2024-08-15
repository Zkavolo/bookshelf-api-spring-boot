package API.Bookshelf.repository;

import API.Bookshelf.model.Book;
import API.Bookshelf.model.Category;
import API.Bookshelf.util.DTO.book.BookDeleteResponseDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer>, JpaSpecificationExecutor<Book> {

    @Query(value = "select * from books where name = :name", nativeQuery = true)
    Optional<Book> findByName(String name);

    @Query(value = "select * from books where id = :id", nativeQuery = true)
    Optional<Book> getOneById(Integer id);

    @Modifying
    @Transactional
    @Query(value = "insert into books (category_id, name, author, publisher, pages, created_at, updated_at,) values " +
            "( " + ":#{#req.categoryId}, "+
            "( " + ":#{#req.name}, "+
            "( " + ":#{#req.author}, "+
            "( " + ":#{#req.publisher}, "+
            "( " + ":#{#req.pages}, "+
            ":#{#req.createdAt}, "+
            ":#{#req.updatedAt} "+
            ")", nativeQuery = true)
    void insertTo(@Param("req") Book req);

    @Modifying
    @Transactional
    @Query(value = "delete from books where id = :id", nativeQuery = true)
    void deleteOneById(Integer id);
}

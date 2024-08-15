package API.Bookshelf.repository;

import API.Bookshelf.model.Book;
import API.Bookshelf.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    @Query(value = "SELECT * FROM categories WHERE category_name = :name", nativeQuery = true)
    Optional<Category> findByName(String name);

    @Query(value = "select * from categories where id = :id", nativeQuery = true)
    Optional<Category> getOneById(Integer id);

    @Modifying
    @Transactional
    @Query(value = "insert into categories (category_name, created_at, updated_at) values " +
            "( " + ":#{#req.categoryName}, "+
            ":#{#req.createdAt}, "+
            ":#{#req.updatedAt} "+
            ")", nativeQuery = true)
    void insertTo(@Param("req") Category req);

    @Modifying
    @Transactional
    @Query(value = "delete from categories where id = :id", nativeQuery = true)
    void deleteOneById(Integer id);
}

package API.Bookshelf.repository;

import API.Bookshelf.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    @Query(value = "SELECT * FROM categories WHERE category_name = :name", nativeQuery = true)
    Optional<Category> findByName(String name);
}

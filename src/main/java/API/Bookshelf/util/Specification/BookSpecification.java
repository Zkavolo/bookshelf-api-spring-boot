package API.Bookshelf.util.Specification;

import API.Bookshelf.model.Book;
import API.Bookshelf.model.Category;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class BookSpecification {
    public static Specification<Book> getSpecification(Category category){
        List<Predicate> predicates = new ArrayList<>();

        return ((root, query, criteriaBuilder) -> {
            if(category != null){
                predicates.add(criteriaBuilder.equal(root.get("category"), category));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
    }
}

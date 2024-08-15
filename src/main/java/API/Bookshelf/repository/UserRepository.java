package API.Bookshelf.repository;

import API.Bookshelf.model.Category;
import API.Bookshelf.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    @Query(value = "select * from users where name = :name", nativeQuery = true)
    Optional<UserEntity> findByName(String name);

    @Query(value = "select * from users where email = :email", nativeQuery = true)
    Optional<UserEntity> findByEmail(String email);

//    @Query(value = "select * from users where id = :id", nativeQuery = true)
//    Optional<UserEntity> getOneById(Integer id);

    @Modifying
    @Transactional
    @Query(value = "insert into users (name, email, password, created_at, updated_at) values " +
            "( " + ":#{#req.name}, "+
            ":#{#req.email}, "+
            ":#{#req.password}, "+
            ":#{#req.createdAt}, "+
            ":#{#req.updatedAt} "+
            ")", nativeQuery = true)
    void insertTo(@Param("req") UserEntity req);

    @Modifying
    @Transactional
    @Query(value = "delete from users where id = :id", nativeQuery = true)
    void deleteOneById(Integer id);
}

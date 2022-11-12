package md.bookstore.dao;

import md.bookstore.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorDAO extends JpaRepository<Author, Long> {

    @Query(value = "SELECT * FROM author OFFSET :offset LIMIT :limit", nativeQuery = true)
    List<Author> findAuthorEntityWithOffsetAndLimit (
            @Param("offset") Integer offset,
            @Param("limit") Integer authorLimit
    );
}

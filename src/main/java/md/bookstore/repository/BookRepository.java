package md.bookstore.repository;

import md.bookstore.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query(value = "SELECT * FROM book OFFSET :offset LIMIT :limit", nativeQuery = true)
    List<Book> findAllWithOffsetAndLimit(
            @Param("offset") Integer offset,
            @Param("limit") Integer bookLimit
    );

    Book getBookByTitle(String title);

//    @Query(value = "SELECT count(b) FROM Book b WHERE b.id IN :ids GROUP BY count(b)")
//    Long countAllById(Iterable<? extends Number> ids);

}
package md.bookstore.repository;

import md.bookstore.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Integer> findAmountById(Long id);

    @Query(
            value = "SELECT DISTINCT b FROM Book b JOIN FETCH b.literaryWorks lw JOIN FETCH lw.author",
            countQuery = "SELECT count(DISTINCT b) FROM Book b JOIN FETCH b.literaryWorks lw JOIN FETCH lw.author"
    )
    List<Book> findAll(Pageable pageable);
}
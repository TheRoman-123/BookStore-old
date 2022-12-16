package md.bookstore.dao;

import md.bookstore.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
    @Query(value = "SELECT * FROM genre OFFSET :offset LIMIT :limit", nativeQuery = true)
    List<Genre> findGenreEntityWithOffsetAndLimit(Integer offset, Integer limit);
}

package md.bookstore.repository;

import md.bookstore.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
    boolean existsByGenreNameIgnoreCase(String genreName);
}

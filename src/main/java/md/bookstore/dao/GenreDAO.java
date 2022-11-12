package md.bookstore.dao;

import md.bookstore.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenreDAO extends JpaRepository<Genre, Long> {
//    public List<Genre> getGenresByIdStartsWith
}

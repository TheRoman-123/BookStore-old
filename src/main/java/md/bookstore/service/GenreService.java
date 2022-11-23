package md.bookstore.service;

import lombok.AllArgsConstructor;
import md.bookstore.dao.GenreDAO;
import md.bookstore.dto.GenreDTO;
import md.bookstore.dto.GenreDTO;
import md.bookstore.entity.Genre;
import md.bookstore.exception.OffsetOrLimitException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GenreService {
    private GenreDAO genreDAO;

    public List<GenreDTO> getAllUntilLimit(Integer offset, Integer limit) {
        if (offset == null || limit == null || limit <= 0 || offset <= 0) {
            throw new OffsetOrLimitException(offset, limit);
        }
        return genreDAO.findGenreEntityWithOffsetAndLimit(offset, limit)
                .parallelStream()
                .map(GenreDTO::new)
                .collect(Collectors.toList());
    }

    public List<GenreDTO> getAll() {
        return genreDAO.findAll()
                .parallelStream()
                .map(GenreDTO::new)
                .collect(Collectors.toList());
    }

    public GenreDTO get(Long id) {
        return new GenreDTO(genreDAO.findById(id).orElseThrow());
    }

    public void createGenre(GenreDTO genreDTO) {
        if (genreDTO == null) {
            throw new NullPointerException("Genre is null. Genre not created.");
        }
        Genre genre = new Genre();
        genre.setGenreName(genreDTO.getGenreName());
        genreDAO.save(genre);
    }

    public void updateGenre(Long id, GenreDTO genreDTO) {
        Genre genre = genreDAO.getReferenceById(id);
        genre.setGenreName(genreDTO.getGenreName());
        genreDAO.save(genre);
    }

    public void deleteGenre(Long id) {
        genreDAO.deleteById(id);
    }
    
}

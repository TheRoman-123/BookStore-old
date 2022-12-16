package md.bookstore.service;

import lombok.AllArgsConstructor;
import md.bookstore.dao.GenreRepository;
import md.bookstore.dto.GenreDTO;
import md.bookstore.entity.Genre;
import md.bookstore.exception.OffsetOrLimitException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GenreService {
    private GenreRepository genreRepository;

    public List<GenreDTO> getAllUntilLimit(Integer offset, Integer limit) {
        if (offset == null || limit == null || limit <= 0 || offset <= 0) {
            throw new OffsetOrLimitException(offset, limit);
        }
        return genreRepository.findGenreEntityWithOffsetAndLimit(offset, limit)
                .parallelStream()
                .map(GenreDTO::new)
                .collect(Collectors.toList());
    }

    public List<GenreDTO> getAll() {
        return genreRepository.findAll()
                .parallelStream()
                .map(GenreDTO::new)
                .collect(Collectors.toList());
    }

    public GenreDTO get(Long id) {
        return new GenreDTO(genreRepository.findById(id).orElseThrow());
    }

    public void createGenre(GenreDTO genreDTO) {
        if (genreDTO == null) {
            throw new NullPointerException("Genre is null. Genre not created.");
        }
        Genre genre = new Genre();
        genre.setGenreName(genreDTO.getGenreName());
        genreRepository.save(genre);
    }

    public void updateGenre(Long id, GenreDTO genreDTO) {
        Genre genre = genreRepository.getReferenceById(id);
        genre.setGenreName(genreDTO.getGenreName());
        genreRepository.save(genre);
    }

    public void deleteGenre(Long id) {
        genreRepository.deleteById(id);
    }
    
}

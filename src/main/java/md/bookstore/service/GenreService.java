package md.bookstore.service;

import lombok.AllArgsConstructor;
import md.bookstore.repository.GenreRepository;
import md.bookstore.dto.GenreDto;
import md.bookstore.entity.Genre;
import md.bookstore.exception.IllegalPageException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GenreService {
    private GenreRepository genreRepository;

    public List<GenreDto> getAllUntilLimit(Integer offset, Integer limit) {
        if (offset == null || limit == null || limit <= 0 || offset <= 0) {
            throw new IllegalPageException(offset, limit);
        }
        return genreRepository.findGenreEntityWithOffsetAndLimit(offset, limit)
                .parallelStream()
                .map(GenreDto::new)
                .collect(Collectors.toList());
    }

    public List<GenreDto> getAll() {
        return genreRepository.findAll()
                .parallelStream()
                .map(GenreDto::new)
                .collect(Collectors.toList());
    }

    public GenreDto get(Long id) {
        return new GenreDto(genreRepository.findById(id).orElseThrow());
    }

    public void createGenre(GenreDto genreDto) {
        if (genreDto == null) {
            throw new NullPointerException("Genre is null. Genre not created.");
        }
        Genre genre = new Genre();
        genre.setGenreName(genreDto.getGenreName());
        genreRepository.save(genre);
    }

    public void updateGenre(Long id, GenreDto genreDto) {
        Genre genre = genreRepository.getReferenceById(id);
        genre.setGenreName(genreDto.getGenreName());
        genreRepository.save(genre);
    }

    public void deleteGenre(Long id) {
        genreRepository.deleteById(id);
    }
    
}

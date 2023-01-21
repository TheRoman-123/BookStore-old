package md.bookstore.service;

import lombok.RequiredArgsConstructor;
import md.bookstore.dto.GenreDto;
import md.bookstore.entity.Genre;
import md.bookstore.repository.GenreRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreService {
    private final CommonService<Genre> commonService;
    private final GenreRepository genreRepository;

    public List<GenreDto> findAll(Integer pageNumber, Integer pageSize, String sortCriteria, boolean desc) {
        return commonService.getAll(
                pageNumber, pageSize, sortCriteria, desc,
                genreRepository,
                GenreDto::new
        );
    }

    public GenreDto getGenreById(Long id) {
        return new GenreDto(genreRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    public Long createGenre(@Valid GenreDto genreDto) {
        if (genreRepository.existsByGenreNameIgnoreCase(genreDto.getGenreName())) {
            throw new EntityExistsException("Genre with provided name already exists");
        }
        Genre genre = new Genre();
        genre.setGenreName(genreDto.getGenreName());
        genreRepository.save(genre);

        return genre.getId();
    }

    public void updateGenre(Long id, GenreDto genreDto) {
        if (genreRepository.existsByGenreNameIgnoreCase(genreDto.getGenreName())) {
            throw new EntityExistsException("Genre with provided name already exists");
        }
        Genre genre = genreRepository.getReferenceById(id);
        genre.setGenreName(genreDto.getGenreName());
        genreRepository.save(genre);
    }

    public void deleteGenre(Long id) {
        genreRepository.deleteById(id);
    }
    
}

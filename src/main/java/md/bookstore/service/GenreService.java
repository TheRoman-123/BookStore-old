package md.bookstore.service;

import lombok.AllArgsConstructor;
import md.bookstore.dao.GenreDAO;
import md.bookstore.dto.AuthorDTO;
import md.bookstore.dto.GenreDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GenreService {
    private GenreDAO genreDAO;

    public List<GenreDTO> getGenreList() {
        return genreDAO.findAll()
                .parallelStream()
                .map(GenreDTO::new)
                .collect(Collectors.toList());
    }



}

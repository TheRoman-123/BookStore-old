package md.bookstore.dto;

import lombok.Data;
import md.bookstore.entity.Genre;

import javax.validation.constraints.Size;

@Data
public class GenreDTO {
    private Long id;
    @Size(max = 20)
    private String genreName;

    public GenreDTO() {}
    public GenreDTO(Genre genre) {
        id = genre.getId();
        genreName = genre.getGenreName();
    }
}

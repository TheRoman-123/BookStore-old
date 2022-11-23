package md.bookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import md.bookstore.entity.Genre;

import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GenreDTO {
    private Long id;
    @Size(max = 20)
    private String genreName;

    public GenreDTO(Genre genre) {
        id = genre.getId();
        genreName = genre.getGenreName();
    }
}

package md.bookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import md.bookstore.entity.Genre;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GenreDto {
    private Long id;
    @NotBlank(message = "Genre name can't be empty")
    @Size(max = 50, message = "Genre name must be less than 50 characters")
    private String genreName;

    public GenreDto(Genre genre) {
        id = genre.getId();
        genreName = genre.getGenreName();
    }
}

package md.bookstore.dto;

import lombok.Data;
import md.bookstore.entity.Author;
import md.bookstore.entity.Genre;

import javax.validation.constraints.Size;

@Data
public class AuthorDTO {
    private Long id;
    @Size(max = 20)
    private String firstName;
    @Size(max = 20)
    private String lastName;

    public AuthorDTO() {}

    public AuthorDTO(Author author) {
        id = author.getId();
        firstName = author.getFirstName();
        lastName = author.getLastName();
    }
}

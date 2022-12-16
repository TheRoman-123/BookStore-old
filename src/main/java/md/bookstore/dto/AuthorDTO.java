package md.bookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import md.bookstore.entity.Author;

import jakarta.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthorDTO {
//    private Long id;
    @Size(max = 20)
    private String firstName;
    @Size(max = 20)
    private String lastName;

    public AuthorDTO(Author author) {
//        id = author.getId();
        firstName = author.getFirstName();
        lastName = author.getLastName();
    }
}

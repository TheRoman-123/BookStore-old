package md.bookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import md.bookstore.entity.Author;

import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthorDto {
//    private Long id;
    @Size(max = 20)
    private String firstName;
    @Size(max = 20)
    private String lastName;

    public AuthorDto(Author author) {
//        id = author.getId();
        firstName = author.getFirstName();
        lastName = author.getLastName();
    }
}

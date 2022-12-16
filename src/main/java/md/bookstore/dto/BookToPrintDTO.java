package md.bookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import md.bookstore.entity.Book;
import md.bookstore.entity.LiteraryWork;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * A DTO for the {@link md.bookstore.entity.Book} entity
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookToPrintDTO implements BookDTO {
    private Long id;
    private Double price;
    private String title;
    private Set<AuthorDTO> authors;
    private String imagePath;

    public BookToPrintDTO(Book book) {
        id = book.getId();
        price = book.getPrice();
        title = book.getTitle();
        imagePath = book.getImagePath();
        authors = book.getLiteraryWorks()
                .stream()
                .map(LiteraryWork::getAuthor)
                .map(AuthorDTO::new)
                .collect(Collectors.toSet());
    }
}
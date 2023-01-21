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
public class BookToPrintDto implements BookDto {
    private Long id;
    private Double price;
    private String title;
    private Set<AuthorDto> authors;

    public BookToPrintDto(Book book) {
        id = book.getId();
        price = book.getPrice();
        title = book.getTitle();
        authors = book.getLiteraryWorks()
                .stream()
                .map(LiteraryWork::getAuthor)
                .map(AuthorDto::new)
                .collect(Collectors.toSet());
    }
}
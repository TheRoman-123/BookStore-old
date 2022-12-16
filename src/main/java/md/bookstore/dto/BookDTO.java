package md.bookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import md.bookstore.entity.Book;

import jakarta.validation.constraints.Size;
import java.io.Serializable;

/**
 * A DTO for the {@link md.bookstore.entity.Book} entity
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDTO {
    private Long id;
    @Size(max = 40)
    private String title;

    public BookDTO(Book book) {
        id = book.getId();
        title = book.getTitle();
    }
}
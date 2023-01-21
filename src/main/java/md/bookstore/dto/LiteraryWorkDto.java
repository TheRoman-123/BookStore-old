package md.bookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import md.bookstore.entity.LiteraryWork;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * A DTO for the {@link md.bookstore.entity.LiteraryWork} entity
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LiteraryWorkDto {
    private Long id;

    @NotBlank(message = "Title can't be empty")
    @Size(min = 1, max = 40, message = "Title must be <= 40 characters")
    private String title;

    public LiteraryWorkDto(LiteraryWork literaryWork) {
        id = literaryWork.getId();
        title = literaryWork.getTitle();
    }
}
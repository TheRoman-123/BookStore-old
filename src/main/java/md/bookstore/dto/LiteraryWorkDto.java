package md.bookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import md.bookstore.entity.LiteraryWork;

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
    @Size(max = 40)
    private String title;

    public LiteraryWorkDto(LiteraryWork literaryWork) {
        id = literaryWork.getId();
        title = literaryWork.getTitle();
    }
}
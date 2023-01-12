package md.bookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookToSaveDto implements BookDto {
    @NotNull(message = "Book price must not be null")
    @Digits(message = "Book price is too big", integer = 5, fraction = 2)
    private Double price;
    @NotBlank(message = "Book title can't be empty")
    @Size(max = 40, message = "Book title must be less than 40 symbols")
    private String title;
    @NotNull(message = "Publisher id can't be null")
    @Min(value = 1, message = "Publisher id can't be negative")
    private Long publisherId;
    @Min(value = 0, message = "Book amount can't be negative")
    private Integer amount;
    private Set<Long> litworkIds;
}

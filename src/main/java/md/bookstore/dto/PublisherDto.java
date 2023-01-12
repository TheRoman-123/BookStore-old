package md.bookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import md.bookstore.entity.Publisher;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * A DTO for the {@link Publisher} entity
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PublisherDto {
    private Long id;
    @NotBlank(message = "Please provide publisher's name")
    @Size(max = 50)
    private String publisherName;

    public PublisherDto(Publisher publisher) {
        id = publisher.getId();
        publisherName = publisher.getPublisherName();
    }
}
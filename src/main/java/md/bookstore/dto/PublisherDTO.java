package md.bookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import md.bookstore.entity.Publisher;

import jakarta.validation.constraints.Size;
import java.io.Serializable;

/**
 * A DTO for the {@link Publisher} entity
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PublisherDTO {
    private Long id;
    @Size(max = 50)
    private String publisherName;

    public PublisherDTO(Publisher publisher) {
        id = publisher.getId();
        publisherName = publisher.getPublisherName();
    }
}
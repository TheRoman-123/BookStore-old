package md.bookstore.dto;

import lombok.Data;
import md.bookstore.entity.Publisher;

import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * A DTO for the {@link Publisher} entity
 */
@Data
public class PublisherDTO {
    private Long id;
    @Size(max = 50)
    private String publisherName;
}
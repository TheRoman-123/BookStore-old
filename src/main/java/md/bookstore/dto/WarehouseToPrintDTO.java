package md.bookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import md.bookstore.entity.Warehouse;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * A DTO for the {@link md.bookstore.entity.Warehouse} entity
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WarehouseToPrintDTO implements WarehouseDTO {
    private Long id;
    private Double price;
    private String title;
    private Set<AuthorDTO> authors;
    private String imagePath;

    public WarehouseToPrintDTO(Warehouse warehouse) {
        id = warehouse.getId();
        price = warehouse.getPrice();
        title = warehouse.getBook().getTitle();
        imagePath = warehouse.getImagePath();
        authors = warehouse.getBook().getAuthors()
                .stream()
                .map(AuthorDTO::new)
                .collect(Collectors.toSet());
    }
}
package md.bookstore.dto;

import lombok.Data;
import md.bookstore.entity.Warehouse;

import javax.validation.constraints.Size;

/**
 * A DTO for the {@link md.bookstore.entity.Warehouse} entity
 */

@Data
public class WarehouseImageDTO implements WarehouseDTO {
    @Size(max = 100)
    private String imagePath;

    public WarehouseImageDTO(String imagePath) {
        this.imagePath = imagePath;
    }

    public WarehouseImageDTO(Warehouse warehouse) {
        this.imagePath = warehouse.getImagePath();
    }
}

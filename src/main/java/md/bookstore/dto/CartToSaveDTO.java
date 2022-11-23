package md.bookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import md.bookstore.entity.Cart;
import md.bookstore.entity.Sale;
import md.bookstore.entity.Warehouse;

/**
 * A DTO for the {@link Cart} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartToSaveDTO {
    private Warehouse book;
    private Integer amount;

    public CartToSaveDTO(Cart cart) {
        amount = cart.getAmount();
        book = cart.getWarehouse();
    }
}
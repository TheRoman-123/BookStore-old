package md.bookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import md.bookstore.entity.Cart;

/**
 * A DTO for the {@link Cart} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartToSaveDto {
    private Long bookId;
    private Integer amount;

    public CartToSaveDto(Cart cart) {
        amount = cart.getAmount();
        bookId = cart.getBook().getId();
    }
}
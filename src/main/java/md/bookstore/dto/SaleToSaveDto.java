package md.bookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * A DTO for the {@link md.bookstore.entity.Sale} entity
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaleToSaveDto {
    private Double cost;
    private CustomerDto customerDto;
    private Set<CartToSaveDto> booksWithSaleAmount;
}

package md.bookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import md.bookstore.entity.Sale;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaleDTO {
    private Long id;
    private LocalDateTime dateTime;
    private Double cost;

    private SaleDTO(Sale sale) {
        id = sale.getId();
        dateTime = sale.getDateTime();
        cost = sale.getCost();
    }
}

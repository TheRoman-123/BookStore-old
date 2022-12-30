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
public class SaleDto {
    private Long id;
    private LocalDateTime dateTime;
    private Double cost;
    private Boolean confirmed;

    public SaleDto(Sale sale) {
        id = sale.getId();
        dateTime = sale.getDateTime();
        cost = sale.getCost();
        confirmed = sale.getConfirmed();
    }
}

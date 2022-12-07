package md.bookstore.controller;

import lombok.AllArgsConstructor;
import md.bookstore.dto.CartToSaveDTO;
import md.bookstore.service.SaleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/sales")
public class SaleController {
    private SaleService saleService;

//    @GetMapping("/{id}")
//    public ResponseEntity<Object> getSale (@PathVariable("id") Long id) {
//        return new ResponseEntity<>(saleService.get(id), HttpStatus.OK);
//    }

    @PostMapping("")
    public ResponseEntity<Object> createSale (
            @RequestParam("cost") Double cost,
            @RequestParam("user_id") Long customer_id,
            @RequestBody Set<CartToSaveDTO> books
    ) {
        return new ResponseEntity<>(
                saleService.createSale(cost, customer_id, books),
                HttpStatus.CREATED
        );
    }
}

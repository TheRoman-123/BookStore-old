package md.bookstore.controller;

import lombok.AllArgsConstructor;
import md.bookstore.dto.CartToSaveDto;
import md.bookstore.entity.User;
import md.bookstore.service.SaleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/sales")
public class SaleController {
    private SaleService saleService;

//    @PreAuthorize("permitAll()")
    @PostMapping("")
    public ResponseEntity<Object> createSale (
            @RequestParam("cost") Double cost,
            @RequestParam("user_id") Long customer_id,
            @RequestBody Set<CartToSaveDto> books
    ) {
        return new ResponseEntity<>(
                saleService.createSale(cost, customer_id, books),
                HttpStatus.CREATED
        );
    }

    @PostMapping("/confirm/{id}")
    public ResponseEntity<Object> confirmSale (@PathVariable("id") Long id) {
        saleService.confirmSale(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/decline/{id}")
    public ResponseEntity<Object> declineSale (@PathVariable("id") Long id) {
        saleService.declineSale(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // To get user's sales
    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("")               // Without {id} because of @AuthenticationPrincipal
    public ResponseEntity<Object> getSales (@AuthenticationPrincipal User user) {
        return new ResponseEntity<>(saleService.getSales(user), HttpStatus.OK);
    }
}

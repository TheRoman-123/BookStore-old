package md.bookstore.controller;

import lombok.RequiredArgsConstructor;
import md.bookstore.dto.CartToSaveDto;
import md.bookstore.service.SaleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sales")
public class SaleController {
    private final SaleService saleService;

    @PostMapping
    public ResponseEntity<Object> createSale (
            @RequestParam(value = "customerName", required = false) String firstName,
            @RequestParam(value = "phoneNumber", required = false) String phoneNumber,
            @RequestParam("cost") Double cost,
            @RequestBody Set<CartToSaveDto> books,
            @AuthenticationPrincipal String email
    ) {
        return new ResponseEntity<>(
                saleService.createSale(cost, books, email, firstName, phoneNumber),
                HttpStatus.CREATED
        );
    }

    @PreAuthorize(value = "hasAuthority('ORDER_PROCESSOR')")    // and ADMIN
    @PostMapping("/confirm/{id}")
    public ResponseEntity<Object> confirmSale (@PathVariable("id") Long id) {
        saleService.confirmSale(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize(value = "hasAuthority('ORDER_PROCESSOR')")    // and ADMIN
    @PostMapping("/decline/{id}")
    public ResponseEntity<Object> declineSale (@PathVariable("id") Long id) {
        saleService.declineSale(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // To get user's sales
    @PreAuthorize("hasAuthority('USER')")       // Разберись с аннотацией. Не работает проверка на аутентификацию.
    @GetMapping
    public ResponseEntity<Object> getSales (
            @AuthenticationPrincipal String email,
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "size", required = false) Integer size
    ) {
        return (page == null || size == null) ?
                ResponseEntity.ok(saleService.getSales(email)) :
                ResponseEntity.ok(saleService.getSales(email, page, size));
    }
}

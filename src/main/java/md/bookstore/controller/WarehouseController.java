package md.bookstore.controller;

import lombok.AllArgsConstructor;
import md.bookstore.service.WarehouseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/warehouses")
public class WarehouseController {
    private WarehouseService warehouseService;

    @GetMapping("")
    public ResponseEntity<Object> getBookList(
            @RequestParam(required = false) Integer offset,
            @RequestParam(required = false) Integer limit
    ) {
        return (offset == null || limit == null) ?
                new ResponseEntity<>(warehouseService.getAll(), HttpStatus.OK) :
                new ResponseEntity<>(warehouseService.getAllUntilLimit(offset, limit), HttpStatus.OK);
    }
}

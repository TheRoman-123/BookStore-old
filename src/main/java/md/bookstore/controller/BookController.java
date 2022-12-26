package md.bookstore.controller;

import lombok.AllArgsConstructor;
import md.bookstore.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/books")
public class BookController {
    private BookService bookService;

    @GetMapping("")
    public ResponseEntity<Object> getBookList(
            @RequestParam(required = false) Integer pageNumber,
            @RequestParam(required = false) Integer pageSize
    ) {
        return (pageNumber == null || pageSize == null) ?
                new ResponseEntity<>(bookService.getAll(), HttpStatus.OK) :
                new ResponseEntity<>(bookService.getAll(pageNumber, pageSize), HttpStatus.OK);
    }
}

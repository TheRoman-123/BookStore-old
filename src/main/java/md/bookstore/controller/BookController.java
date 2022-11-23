package md.bookstore.controller;

import lombok.RequiredArgsConstructor;
import md.bookstore.dto.BookDTO;
import md.bookstore.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    @GetMapping("")
    public ResponseEntity<Object> getBookList(
            @RequestParam(required = false) Integer offset,
            @RequestParam(required = false) Integer limit
    ) {
        return (offset == null || limit == null) ?
                new ResponseEntity<>(bookService.getAll(), HttpStatus.OK) :
                new ResponseEntity<>(bookService.getAllUntilLimit(offset, limit), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getBookById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(bookService.get(id), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Object> createBook(@RequestBody BookDTO bookDTO) {
        bookService.createBook(bookDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
        // HttpStatus.NoContent, если в теле ничего не передаём. По идее надо использовать его в моём случае.
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateBook(@PathVariable("id") Long id, @RequestBody BookDTO bookDTO) {
        bookService.updateBook(id, bookDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteBook(@PathVariable("id") Long id) {
        bookService.deleteBook(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

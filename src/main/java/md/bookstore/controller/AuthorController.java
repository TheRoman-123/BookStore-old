package md.bookstore.controller;


import lombok.AllArgsConstructor;
import md.bookstore.dto.AuthorDTO;
import md.bookstore.service.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/authors")
public class AuthorController {
    private AuthorService authorService;

    @GetMapping("/")
    public ResponseEntity<Object> getAuthorList(
            @RequestParam(required = false) Integer offset,
            @RequestParam(required = false) Integer limit
    ) {
        return (offset == null || limit == null) ?
                new ResponseEntity<>(authorService.getAll(), HttpStatus.OK) :
                new ResponseEntity<>(authorService.getAllUntilLimit(offset, limit), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getAuthorById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(authorService.get(id), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Object> createAuthor(@RequestBody AuthorDTO authorDTO) {
        authorService.createAuthor(authorDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
        // HttpStatus.NoContent, если в теле ничего не передаём. По идее надо использовать его в моём случае.
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateAuthor(@PathVariable("id") Long id, @RequestBody AuthorDTO authorDTO) {
        authorService.updateAuthor(id, authorDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteAuthor(@PathVariable("id") Long id) {
        authorService.deleteAuthor(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

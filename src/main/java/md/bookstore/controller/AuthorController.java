package md.bookstore.controller;


import lombok.RequiredArgsConstructor;
import md.bookstore.dto.AuthorDto;
import md.bookstore.service.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("authors")
public class AuthorController {
    private final AuthorService authorService;

    @GetMapping
    public ResponseEntity<Object> getAuthorList(
            @RequestParam Integer pageNumber,
            @RequestParam Integer pageSize,
            @RequestParam String sortCriteria,
            @RequestParam boolean desc
    ) {
        return ResponseEntity.ok(authorService.findAll(pageNumber, pageSize, sortCriteria, desc));
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> getAuthorById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(authorService.findAuthorById(id));
    }

    @PostMapping
    public ResponseEntity<Object> createAuthor(@RequestBody AuthorDto authorDto) {
        return new ResponseEntity<>(
                authorService.createAuthor(authorDto),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateAuthor(
            @PathVariable("id") Long id,
            @RequestBody AuthorDto authorDto
    ) {
        authorService.updateAuthor(id, authorDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteAuthor(@PathVariable("id") Long id) {
        authorService.deleteAuthor(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

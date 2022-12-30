package md.bookstore.controller;

import lombok.AllArgsConstructor;
import md.bookstore.dto.GenreDto;
import md.bookstore.service.GenreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/genres")
public class GenreController {

    private GenreService genreService;

    @GetMapping("/")
    public ResponseEntity<Object> getGenreList(
            @RequestParam(required = false) Integer offset,
            @RequestParam(required = false) Integer limit
    ) {
        return (offset == null || limit == null) ?
                new ResponseEntity<>(genreService.getAll(), HttpStatus.OK) :
                new ResponseEntity<>(genreService.getAllUntilLimit(offset, limit), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getGenreById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(genreService.get(id), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Object> createGenre(@RequestBody GenreDto genreDto) {
        genreService.createGenre(genreDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
        // HttpStatus.NoContent, если в теле ничего не передаём. По идее надо использовать его в моём случае.
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateGenre(@PathVariable("id") Long id, @RequestBody GenreDto genreDto) {
        genreService.updateGenre(id, genreDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteGenre(@PathVariable("id") Long id) {
        genreService.deleteGenre(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}

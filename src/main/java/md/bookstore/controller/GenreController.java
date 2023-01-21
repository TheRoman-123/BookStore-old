package md.bookstore.controller;

import lombok.RequiredArgsConstructor;
import md.bookstore.dto.GenreDto;
import md.bookstore.service.GenreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/genres")
public class GenreController {

    private final GenreService genreService;

    @GetMapping
    public ResponseEntity<Object> getGenreList(
            @RequestParam Integer pageNumber,
            @RequestParam Integer pageSize,
            @RequestParam String sortCriteria,
            @RequestParam boolean desc
    ) {
        return ResponseEntity.ok(genreService.findAll(pageNumber, pageSize, sortCriteria, desc));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getGenreById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(genreService.getGenreById(id));
    }

    @PostMapping
    public ResponseEntity<Object> createGenre(@RequestBody GenreDto genreDto) {
        return new ResponseEntity<>(genreService.createGenre(genreDto), HttpStatus.CREATED);
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

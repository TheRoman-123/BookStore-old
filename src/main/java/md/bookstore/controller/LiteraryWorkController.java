package md.bookstore.controller;

import lombok.RequiredArgsConstructor;
import md.bookstore.dto.LiteraryWorkDto;
import md.bookstore.service.LiteraryWorkService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/literaryWorks")
public class LiteraryWorkController {
    private final LiteraryWorkService literaryWorkService;

    @GetMapping("")
    public ResponseEntity<Object> getLiteraryWorkList(
            @RequestParam(required = false) Integer offset,
            @RequestParam(required = false) Integer limit
    ) {
        return (offset == null || limit == null) ?
                new ResponseEntity<>(literaryWorkService.getAll(), HttpStatus.OK) :
                new ResponseEntity<>(literaryWorkService.getAllUntilLimit(offset, limit), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getLiteraryWorkById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(literaryWorkService.get(id), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Object> createLiteraryWork(@RequestBody LiteraryWorkDto literaryWorkDto) {
        literaryWorkService.createLiteraryWork(literaryWorkDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
        // HttpStatus.NoContent, если в теле ничего не передаём. По идее надо использовать его в моём случае.
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateLiteraryWork(@PathVariable("id") Long id, @RequestBody LiteraryWorkDto literaryWorkDto) {
        literaryWorkService.updateLiteraryWork(id, literaryWorkDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteLiteraryWork(@PathVariable("id") Long id) {
        literaryWorkService.deleteLiteraryWork(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

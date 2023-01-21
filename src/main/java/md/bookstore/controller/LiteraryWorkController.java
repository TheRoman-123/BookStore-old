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
            @RequestParam Integer pageNumber,
            @RequestParam Integer pageSize,
            @RequestParam String sortCriteria,
            @RequestParam boolean desc
    ) {
        return ResponseEntity.ok(literaryWorkService.findAll(pageNumber, pageSize, sortCriteria, desc));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getLiteraryWorkById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(literaryWorkService.findLiteraryWorkById(id));
    }

    @PostMapping("")
    public ResponseEntity<Object> createLiteraryWork(@RequestBody LiteraryWorkDto literaryWorkDto) {
        return new ResponseEntity<>(literaryWorkService.createLiteraryWork(literaryWorkDto), HttpStatus.CREATED);
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

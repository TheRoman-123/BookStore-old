package md.bookstore.controller;

import lombok.AllArgsConstructor;
import md.bookstore.dto.PublisherDTO;
import md.bookstore.service.PublisherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/publishers")
public class PublisherController {
    private PublisherService publisherService;

    @GetMapping("/")
    public ResponseEntity<Object> getPublisherList(
            @RequestParam(required = false) Integer offset,
            @RequestParam(required = false) Integer limit
    ) {
        return (offset == null || limit == null) ?
                new ResponseEntity<>(publisherService.getAll(), HttpStatus.OK) :
                new ResponseEntity<>(publisherService.getAllUntilLimit(offset, limit), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getPublisherById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(publisherService.get(id), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Object> createPublisher(@RequestBody PublisherDTO publisherDTO) {
        publisherService.createPublisher(publisherDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
        // HttpStatus.NoContent, если в теле ничего не передаём. По идее надо использовать его в моём случае.
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updatePublisher(@PathVariable("id") Long id, @RequestBody PublisherDTO publisherDTO) {
        publisherService.updatePublisher(id, publisherDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePublisher(@PathVariable("id") Long id) {
        publisherService.deletePublisher(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

package md.bookstore.controller;

import lombok.AllArgsConstructor;
import md.bookstore.dto.PublisherDto;
import md.bookstore.service.PublisherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/publishers")
public class PublisherController {
    private PublisherService publisherService;

    @GetMapping
    public ResponseEntity<Object> getPublisherList(
            @RequestParam Integer pageNumber,
            @RequestParam Integer pageSize,
            @RequestParam String sortCriteria,
            @RequestParam boolean desc
    ) {
        return ResponseEntity.ok(publisherService.findAll(pageNumber, pageSize, sortCriteria, desc));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getPublisherById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(publisherService.getPublisherDtoById(id), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('CONTENT_MANAGER', 'ADMIN')")
    @PostMapping
    public ResponseEntity<Object> createPublisher(@RequestBody @Valid PublisherDto publisherDto) {
        return new ResponseEntity<>(
                publisherService.createPublisher(publisherDto),
                HttpStatus.CREATED
        );
    }

    @PreAuthorize("hasAnyAuthority('CONTENT_MANAGER', 'ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Object> updatePublisher(@PathVariable("id") Long id, @RequestBody PublisherDto publisherDto) {
        publisherService.updatePublisher(id, publisherDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('CONTENT_MANAGER', 'ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePublisher(@PathVariable("id") Long id) {
        publisherService.deletePublisher(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

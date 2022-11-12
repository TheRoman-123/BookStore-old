package md.bookstore.controller;


import lombok.AllArgsConstructor;
import md.bookstore.dto.AuthorDTO;
import md.bookstore.service.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
@AllArgsConstructor
@RequestMapping("/authorDTO")
public class AuthorController {
    private AuthorService authorService;

    @CrossOrigin
    @GetMapping("/")
    public ResponseEntity<Object> getAuthorList(
            @RequestParam(required = false) Integer offset,
            @RequestParam(required = false) Integer limit
    ) {
        try {
            if (offset == null || limit == null) {
                return new ResponseEntity<>(authorService.getAll(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(authorService.getAllUntilLimit(offset, limit), HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/")
    public ResponseEntity<Object> createAuthor(@RequestBody AuthorDTO authorDTO) {
        try {
            authorService.createAuthor(authorDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
//            logger
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/id")
    public ResponseEntity<Object> updateAuthor(
            @PathParam("id") Long id,
            @RequestBody AuthorDTO authorDTO
    ) {
        try {
            authorService.updateAuthor(id, authorDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
//            logger
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/id")
    public ResponseEntity<Object> deleteAuthor(@PathParam("id") Long id) {
        try {
            authorService.deleteAuthor(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
//            logger
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}

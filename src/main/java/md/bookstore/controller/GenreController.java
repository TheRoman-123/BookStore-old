package md.bookstore.controller;

import lombok.AllArgsConstructor;
import md.bookstore.service.GenreService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/genre")
public class GenreController {

    private GenreService genreService;


}

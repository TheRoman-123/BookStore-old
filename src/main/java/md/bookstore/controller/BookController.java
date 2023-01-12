package md.bookstore.controller;

import lombok.AllArgsConstructor;
import md.bookstore.dto.BookToSaveDto;
import md.bookstore.exception.ImageSizeExceededException;
import md.bookstore.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

import static md.bookstore.Constants.MAX_IMAGE_SIZE;

@RestController
@AllArgsConstructor
@RequestMapping("/books")
public class BookController {
    private BookService bookService;

//     BookController извлекает сразу большое количество текстовых данных о книгах.
//     Затем по мере прокрутки страницы отсылает запросы на подгрузку изображений книг
//     (по 4, 8, 3, 6 - в зависимости от того, сколько помещается на страницу в ширину).

    @GetMapping
    public ResponseEntity<Object> getBookList(
            @RequestParam Integer pageNumber,
            @RequestParam Integer pageSize,
            @RequestParam String sortCriteria,
            @RequestParam boolean desc
    ) {
        return ResponseEntity.ok(bookService.getAll(pageNumber, pageSize, sortCriteria, desc));
    }

    @GetMapping("/images")
    public ResponseEntity<Object> getBookImagesById(List<Long> bookIds) {
        return ResponseEntity.ok(bookService.getBookImages(bookIds));
    }

    @PreAuthorize("hasAnyAuthority('CONTENT_MANAGER', 'ADMIN')")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> createBook(
            @Valid @RequestPart("jsonData") BookToSaveDto book,
            @RequestPart("image") MultipartFile image,
            BindingResult bindingResult
    ) throws IOException {
        if(image.getSize() > MAX_IMAGE_SIZE){
            throw new ImageSizeExceededException(image.getSize());
        }
        if (bindingResult.hasErrors()) {
            throw new IllegalArgumentException(bindingResult.toString());
        }
        return new ResponseEntity<>(
                bookService.createBook(book, image),
                HttpStatus.CREATED
        );
    }
}

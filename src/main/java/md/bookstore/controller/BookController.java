package md.bookstore.controller;

import lombok.RequiredArgsConstructor;
import md.bookstore.dto.BookToSaveDto;
import md.bookstore.exception.ImageSizeExceededException;
import md.bookstore.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static md.bookstore.Constants.MAX_IMAGE_SIZE;

@RestController
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

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
        return ResponseEntity.ok(bookService.findAll(pageNumber, pageSize, sortCriteria, desc));
    }

    @GetMapping("/image/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable("id") Long id)
            throws IOException {
        return bookService.getBookImageById(id);
    }

    @PreAuthorize("hasAnyAuthority('CONTENT_MANAGER', 'ADMIN')")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> createBook(
            @RequestPart("jsonData") BookToSaveDto book,
            @RequestPart("image") MultipartFile image
    ) throws IOException {
        if (image.getSize() > MAX_IMAGE_SIZE){
            throw new ImageSizeExceededException(image.getSize());
        }
        return new ResponseEntity<>(
                bookService.createBook(book, image),
                HttpStatus.CREATED
        );
    }
}

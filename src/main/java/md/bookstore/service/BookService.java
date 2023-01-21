package md.bookstore.service;

import lombok.RequiredArgsConstructor;
import md.bookstore.dto.BookToPrintDto;
import md.bookstore.dto.BookToSaveDto;
import md.bookstore.dto.converter.BookDtoConverter;
import md.bookstore.entity.Book;
import md.bookstore.exception.EmptyFieldException;
import md.bookstore.exception.NotEnoughBooksException;
import md.bookstore.repository.BookRepository;
import md.bookstore.repository.LiteraryWorkRepository;
import md.bookstore.repository.PublisherRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

import static md.bookstore.Constants.IMAGE_FOLDER;

@Service
@RequiredArgsConstructor
public class BookService {
    private final CommonService<Book> commonService;
    private final BookRepository bookRepository;
    private final LiteraryWorkRepository literaryWorkRepository;
    private final PublisherRepository publisherRepository;

    public BookToPrintDto findBookById(Long bookId) {
        return new BookToPrintDto(bookRepository.findById(bookId).orElseThrow(ResourceNotFoundException::new));
    }

    public List<BookToPrintDto> findAll(Integer pageNumber, Integer pageSize, String sortCriteria, boolean desc) {
        return commonService.getAll(
                pageNumber, pageSize, sortCriteria, desc,
                bookRepository,
                BookToPrintDto::new
        );
    }

    public boolean hasWarehouseEnoughBooks(Long id, Integer amount) {
        return bookRepository.findAmountById(id).orElse(0) >= amount;
    }

    public void takeFromWarehouse(Book book, Integer amount) {
        if (!hasWarehouseEnoughBooks(book.getId(), amount)) {
            throw new NotEnoughBooksException("Not enough " + book.getTitle());
        }
        book.setAmount(book.getAmount() - amount);
    }

    public Long createBook(@Valid BookToSaveDto bookToSaveDto, MultipartFile image)
            throws IOException {
        Book book = BookDtoConverter.fromDto(bookToSaveDto);

        book.setPublisher(
                publisherRepository.getReferenceById(bookToSaveDto.getPublisherId())
        );

        book.setLiteraryWorks(
                literaryWorkRepository.getReferencesByIdIn(bookToSaveDto.getLitworkIds())
        );

        if (image != null && !image.isEmpty()) {
            String fileName = String.format("%d.%s", book.getId(),
                    Objects.requireNonNull(image.getContentType()).split("/")[1]);
            Path file = Path.of(IMAGE_FOLDER, fileName);
            image.transferTo(file);
            book.setImagePath(file.toString());
        }

        bookRepository.save(book);

        return book.getId();
    }

    public byte[] getBookImageById(Long id) throws IOException {
        Book book = bookRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        if (book.getImagePath() == null) {
            throw new EmptyFieldException("book", "imagePath");
        }
        Path path = Path.of(book.getImagePath());
        if (!Files.exists(path)) {
            throw new FileNotFoundException("Image doesn't exist or is unreachable");
        }
        return Files.readAllBytes(path);
    }
}

package md.bookstore.service;

import lombok.AllArgsConstructor;
import md.bookstore.dto.BookImagesDto;
import md.bookstore.dto.BookToSaveDto;
import md.bookstore.dto.converter.BookDtoConverter;
import md.bookstore.entity.Book;
import md.bookstore.exception.NotEnoughBooksException;
import md.bookstore.repository.BookRepository;
import md.bookstore.dto.BookToPrintDto;
import md.bookstore.repository.LiteraryWorkRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class BookService {
    private PublisherService publisherService;
    private BookRepository bookRepository;
    private LiteraryWorkRepository literaryWorkRepository;

    public BookToPrintDto getBook(Long bookId) {
        return new BookToPrintDto(bookRepository.findById(bookId).orElseThrow(ResourceNotFoundException::new));
    }

    public List<BookToPrintDto> getAll(Integer pageNumber, Integer pageSize, String sortCriteria, boolean desc) {
        return new CommonService<Book>().getAll(
                pageNumber, pageSize, sortCriteria, desc,
                bookRepository,
                BookToPrintDto::new
        );
    }

    public BookImagesDto getBookImages(List<Long> bookIds) {
        return new BookImagesDto(bookRepository.findAllById(bookIds));
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

    public Long createBook(BookToSaveDto bookToSaveDto, MultipartFile image)
            throws IOException {
        Book book = BookDtoConverter.fromDto(bookToSaveDto);

        book.setPublisher(
                publisherService.getPublisherById(bookToSaveDto.getPublisherId())
        );

        book.setLiteraryWorks(
                literaryWorkRepository.findAllByIdIn(bookToSaveDto.getLitworkIds())
        );

        book.setImage(image.getBytes());

        bookRepository.save(book);

        return book.getId();
    }
}

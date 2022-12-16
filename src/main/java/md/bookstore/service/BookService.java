package md.bookstore.service;

import lombok.AllArgsConstructor;
import md.bookstore.repository.BookRepository;
import md.bookstore.dto.BookDTO;
import md.bookstore.entity.Book;
import md.bookstore.exception.OffsetOrLimitException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookService {
    private BookRepository bookRepository;

    // Later implement Pageable!
    public List<BookDTO> getAllUntilLimit(Integer offset, Integer limit) {
        if (offset == null || limit == null || limit <= 0 || offset <= 0) {
            throw new OffsetOrLimitException(offset, limit);
        }
        return bookRepository.findBookEntityWithOffsetAndLimit(offset, limit)
                .parallelStream()
                .map(BookDTO::new)
                .collect(Collectors.toList());
    }

    public List<BookDTO> getAll() {
        return bookRepository.findAll()
                .parallelStream()
                .map(BookDTO::new)
                .collect(Collectors.toList());
    }

    public BookDTO get(Long id) {
        return new BookDTO(bookRepository.findById(id).orElseThrow());
    }

    public void createBook(BookDTO bookDTO) {
        if (bookDTO == null) {
            throw new NullPointerException("Book is null. Book not created.");
        }
        Book book = new Book();
        book.setTitle(bookDTO.getTitle());
        bookRepository.save(book);
    }

    public void updateBook(Long id, BookDTO bookDTO) {
        Book book = bookRepository.getReferenceById(id);
        book.setTitle(bookDTO.getTitle());
        bookRepository.save(book);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}

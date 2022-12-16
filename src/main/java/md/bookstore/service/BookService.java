package md.bookstore.service;

import lombok.AllArgsConstructor;
import md.bookstore.dao.BookRepository;
import md.bookstore.dto.BookToPrintDTO;
import md.bookstore.exception.OffsetOrLimitException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookService {
    private BookRepository bookRepository;

    public BookToPrintDTO getBook() {
        return null;
    }

    public List<BookToPrintDTO> getAll() {
        return bookRepository.findAll()
                .stream()
                .map(BookToPrintDTO::new)
                .collect(Collectors.toList());
    }


    public List<BookToPrintDTO> getAllUntilLimit(Integer offset, Integer limit) {
        if (offset == null || limit == null || limit <= 0 || offset <= 0) {
            throw new OffsetOrLimitException(offset, limit);
        }
        return bookRepository.findAllWithOffsetAndLimit(offset, limit)
                .stream()
                .map(BookToPrintDTO::new)
                .collect(Collectors.toList());
    }

//    public List<>
}

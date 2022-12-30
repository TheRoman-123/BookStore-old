package md.bookstore.service;

import lombok.AllArgsConstructor;
import md.bookstore.entity.Book;
import md.bookstore.exception.NotEnoughBooksException;
import md.bookstore.repository.BookRepository;
import md.bookstore.dto.BookToPrintDto;
import md.bookstore.exception.IllegalPageException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookService {
    private BookRepository bookRepository;

    public BookToPrintDto getBook() {
        return null;
    }

    public List<BookToPrintDto> getAll() {
        return bookRepository.findAll()
                .stream()
                .map(BookToPrintDto::new)
                .collect(Collectors.toList());
    }


    public List<BookToPrintDto> getAll(Integer pageNumber, Integer pageSize) {
        if (pageNumber == null || pageSize == null || pageSize <= 0 || pageNumber <= 0) {
            throw new IllegalPageException(pageNumber, pageSize);
//            TODO: Create other custom Exception for retrieving data by pages
        }
        // TODO: Sort needs to be obtained from frontend
        Sort sort = Sort.by("title").ascending();
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        return bookRepository.findAll(pageable)
                .stream()
                .map(BookToPrintDto::new)
                .collect(Collectors.toList());
    }

    public boolean hasWarehouseEnoughBooks(Long id, Integer amount) {
        Book book = bookRepository.getReferenceById(id);
        return book.getAmount() >= amount;
    }

    public void takeFromWarehouse(Book book, Integer amount) {
        if (!hasWarehouseEnoughBooks(book.getId(), amount)) {
            throw new NotEnoughBooksException("Not enough " + book.getTitle());
        }
        book.setAmount(book.getAmount() - amount);
    }

//    public List<>
}

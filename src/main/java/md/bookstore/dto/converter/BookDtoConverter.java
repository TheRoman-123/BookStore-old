package md.bookstore.dto.converter;

import md.bookstore.dto.BookToSaveDto;
import md.bookstore.entity.Book;

public class BookDtoConverter {
    public static Book fromDto(BookToSaveDto bookToSaveDto) {
        Book book = new Book();
        book.setTitle(bookToSaveDto.getTitle());
        book.setPrice(bookToSaveDto.getPrice());
        book.setAmount(bookToSaveDto.getAmount());
        return book;
    }
}

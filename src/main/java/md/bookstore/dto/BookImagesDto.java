package md.bookstore.dto;


import lombok.Data;
import lombok.RequiredArgsConstructor;
import md.bookstore.entity.Book;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@RequiredArgsConstructor
public class BookImagesDto implements BookDto {
    private final Map<Long, byte[]> bookImages;

    public BookImagesDto(List<Book> bookList) {
        bookImages = new HashMap<>(bookList.size());
        bookList.forEach(book -> bookImages.put(book.getId(), book.getImage()));
    }
}

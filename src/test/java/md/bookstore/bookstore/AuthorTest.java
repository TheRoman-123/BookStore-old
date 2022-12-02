package md.bookstore.bookstore;

import md.bookstore.dto.AuthorDTO;
import md.bookstore.entity.Author;
import md.bookstore.service.AuthorService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AuthorTest {
    @InjectMocks
    private AuthorDTO authorDTO;

    @InjectMocks
    private AuthorService authorService;

    @Test
    public void testServiceCreateAuthor() {
        authorDTO.setFirstName("Jora");
        authorDTO.setLastName("Avosi");
        doNothing().when(authorService).createAuthor(authorDTO);
        doThrow(new RuntimeException("Null"));

        verify(authorService, times(1))
                .createAuthor(authorDTO);

    }
}

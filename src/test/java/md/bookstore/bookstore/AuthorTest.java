package md.bookstore.bookstore;

import md.bookstore.dto.AuthorDto;
import md.bookstore.service.AuthorService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AuthorTest {
    @InjectMocks
    private AuthorDto authorDto;

    @InjectMocks
    private AuthorService authorService;

    @Test
    public void testServiceCreateAuthor() {
        authorDto.setFirstName("Jora");
        authorDto.setLastName("Avosi");
        doNothing().when(authorService).createAuthor(authorDto);
        doThrow(new RuntimeException("Null"));

        verify(authorService, times(1))
                .createAuthor(authorDto);

    }
}

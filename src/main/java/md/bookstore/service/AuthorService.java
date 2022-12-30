package md.bookstore.service;

import lombok.AllArgsConstructor;
import md.bookstore.dto.AuthorDto;
import md.bookstore.repository.AuthorRepository;
import md.bookstore.entity.Author;
import md.bookstore.exception.IllegalPageException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    // Later implement Pageable!
    public List<AuthorDto> getAllUntilLimit(Integer offset, Integer limit) {
        if (offset == null || limit == null || limit <= 0 || offset <= 0) {
            throw new IllegalPageException(offset, limit);
        }
        return authorRepository.findAuthorEntityWithOffsetAndLimit(offset, limit)
                .parallelStream()
                .map(AuthorDto::new)
                .collect(Collectors.toList());
    }

    public List<AuthorDto> getAll() {
        /*
        List<Author> authorList = authorRepository.findAll();
        List<AuthorDto> authorDtoList = new ArrayList<>();
        for (Author author : authorList) {
            authorDtoList.add(new AuthorDto(author));
        }
        return authorDtoList;
        */

        return authorRepository.findAll()
                .parallelStream()
                .map(AuthorDto::new)
                .collect(Collectors.toList());
    }

    public AuthorDto get(Long id) {
        return new AuthorDto(authorRepository.findById(id).orElseThrow());
    }

    public Long createAuthor(AuthorDto authorDto) {
        if (authorDto == null) {
            throw new NullPointerException("Author.ts is null. Author.ts not created.");
        }
        Author author = new Author();
        author.setFirstName(authorDto.getFirstName());
        author.setLastName(authorDto.getLastName());
        authorRepository.save(author);
        return author.getId();
    }

    public void updateAuthor(Long id, AuthorDto authorDto) {
        Author author = authorRepository.getReferenceById(id);
        author.setFirstName(authorDto.getFirstName());
        author.setLastName(authorDto.getLastName());
        authorRepository.save(author);
    }

    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }
}

package md.bookstore.service;

import lombok.RequiredArgsConstructor;
import md.bookstore.dto.AuthorDto;
import md.bookstore.entity.Author;
import md.bookstore.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService {
    private final CommonService<Author> commonService;
    private final AuthorRepository authorRepository;

    public List<AuthorDto> findAll(Integer pageNumber, Integer pageSize, String sortCriteria, boolean desc) {
        return commonService.getAll(
                pageNumber, pageSize, sortCriteria, desc,
                authorRepository,
                AuthorDto::new
        );
    }

    public AuthorDto findAuthorById(Long id) {
        return new AuthorDto(authorRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    public Long createAuthor(@Valid AuthorDto authorDto) {
        if (authorRepository.existsByFirstNameIgnoreCaseAndLastNameIgnoreCase(
                authorDto.getFirstName(), authorDto.getLastName()
        )) {
            throw new EntityExistsException("Author with provided info already exists");
        }
        Author author = new Author();
        author.setFirstName(authorDto.getFirstName());
        author.setLastName(authorDto.getLastName());
        authorRepository.save(author);

        return author.getId();
    }

    public void updateAuthor(Long id, AuthorDto authorDto) {
        if (authorRepository.existsByFirstNameIgnoreCaseAndLastNameIgnoreCase(
                authorDto.getFirstName(), authorDto.getLastName()
        )) {
            throw new EntityExistsException("Author with provided info already exists");
        }
        Author author = authorRepository.getReferenceById(id); // Надо попробовать
        author.setFirstName(authorDto.getFirstName());
        author.setLastName(authorDto.getLastName());
        authorRepository.save(author);
    }

    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }
}

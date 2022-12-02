package md.bookstore.service;

import lombok.AllArgsConstructor;
import md.bookstore.dao.AuthorDAO;
import md.bookstore.dto.AuthorDTO;
import md.bookstore.entity.Author;
import md.bookstore.exception.OffsetOrLimitException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AuthorService {

    private AuthorDAO authorDAO;

    // Later implement Pageable!
    public List<AuthorDTO> getAllUntilLimit(Integer offset, Integer limit) {
        if (offset == null || limit == null || limit <= 0 || offset <= 0) {
            throw new OffsetOrLimitException(offset, limit);
        }
        return authorDAO.findAuthorEntityWithOffsetAndLimit(offset, limit)
                .parallelStream()
                .map(AuthorDTO::new)
                .collect(Collectors.toList());
    }

    public List<AuthorDTO> getAll() {
        /*
        List<Author> authorList = authorDAO.findAll();
        List<AuthorDTO> authorDTOList = new ArrayList<>();
        for (Author author : authorList) {
            authorDTOList.add(new AuthorDTO(author));
        }
        return authorDTOList;
        */

        return authorDAO.findAll()
                .parallelStream()
                .map(AuthorDTO::new)
                .collect(Collectors.toList());
    }

    public AuthorDTO get(Long id) {
        return new AuthorDTO(authorDAO.findById(id).orElseThrow());
    }

    public void createAuthor(AuthorDTO authorDTO) {
        if (authorDTO == null) {
            throw new NullPointerException("Author.ts is null. Author.ts not created.");
        }
        Author author = new Author();
        author.setFirstName(authorDTO.getFirstName());
        author.setLastName(authorDTO.getLastName());
        authorDAO.save(author);
    }

    public void updateAuthor(Long id, AuthorDTO authorDTO) {
        Author author = authorDAO.getReferenceById(id);
        author.setFirstName(authorDTO.getFirstName());
        author.setLastName(authorDTO.getLastName());
        authorDAO.save(author);
    }

    public void deleteAuthor(Long id) {
        authorDAO.deleteById(id);
    }
}

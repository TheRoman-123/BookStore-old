package md.bookstore.service;

import lombok.AllArgsConstructor;
import md.bookstore.dao.PublisherDAO;
import md.bookstore.dto.PublisherDTO;
import md.bookstore.entity.Publisher;
import md.bookstore.exception.OffsetOrLimitException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PublisherService {
    private PublisherDAO publisherDAO;

    // Later implement Pageable!
    public List<PublisherDTO> getAllUntilLimit(Integer offset, Integer limit) {
        if (offset == null || limit == null || limit <= 0 || offset <= 0) {
            throw new OffsetOrLimitException(offset, limit);
        }
        return publisherDAO.findPublisherEntityWithOffsetAndLimit(offset, limit)
                .parallelStream()
                .map(PublisherDTO::new)
                .collect(Collectors.toList());
    }

    public List<PublisherDTO> getAll() {
        return publisherDAO.findAll()
                .parallelStream()
                .map(PublisherDTO::new)
                .collect(Collectors.toList());
    }

    public PublisherDTO get(Long id) {
        return new PublisherDTO(publisherDAO.findById(id).orElseThrow());
    }

    public void createPublisher(PublisherDTO publisherDTO) {
        if (publisherDTO == null) {
            throw new NullPointerException("Publisher is null. Publisher not created.");
        }
        Publisher publisher = new Publisher();
        publisher.setPublisherName(publisherDTO.getPublisherName());
        publisherDAO.save(publisher);
    }

    public void updatePublisher(Long id, PublisherDTO publisherDTO) {
        Publisher publisher = publisherDAO.getReferenceById(id);
        publisher.setPublisherName(publisherDTO.getPublisherName());
        publisherDAO.save(publisher);
    }

    public void deletePublisher(Long id) {
        publisherDAO.deleteById(id);
    }
}